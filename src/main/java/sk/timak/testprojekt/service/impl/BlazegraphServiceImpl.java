package sk.timak.testprojekt.service.impl;

import org.openrdf.OpenRDFException;
import org.openrdf.query.*;
import org.openrdf.query.resultio.sparqljson.SPARQLResultsJSONWriter;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import sk.timak.testprojekt.component.RepositoryComponent;
import sk.timak.testprojekt.controller.advice.exception.WrongFormatException;
import sk.timak.testprojekt.controller.advice.exception.WrongQueryException;
import sk.timak.testprojekt.domain.searchendpoint.FulltextSearchNotFound;
import sk.timak.testprojekt.domain.searchendpoint.FulltextSearchResponse;
import sk.timak.testprojekt.domain.searchendpoint.Match;
import sk.timak.testprojekt.domain.searchendpoint.Search;
import sk.timak.testprojekt.domain.searchentity.missing.MissingEntity;
import sk.timak.testprojekt.domain.searchentity.simple.Entity;
import sk.timak.testprojekt.domain.searchentity.simple.EntityObject;
import sk.timak.testprojekt.domain.searchentity.simple.SimpleEntity;
import sk.timak.testprojekt.service.BlazegraphService;

import java.io.*;
import java.util.List;

@Service
public class BlazegraphServiceImpl implements BlazegraphService {

    private static final Logger logger = LoggerFactory.getLogger(BlazegraphServiceImpl.class);

    @Value("${resource.path.ontology}")
    private String ontologyResourcePath;

    @Value("${base.url}")
    private String urlProject;

    @Value("#{'${resource.oval}'.split(',')}")
    private List<String> ovalDataList;

    @Autowired
    private RepositoryComponent repositoryComponent;

    @Override
    public String getResult(String query) throws RepositoryException {
        query = query.replaceAll("%20"," ");

        RepositoryConnection repositoryConnection = null;
        try {
            repositoryConnection = repositoryComponent.getRepository().getConnection();

            final TupleQuery tupleQuery = repositoryConnection
                    .prepareTupleQuery(QueryLanguage.SPARQL,
                            query);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            tupleQuery.evaluate(new SPARQLResultsJSONWriter(stream));

            final TupleQueryResult result = tupleQuery.evaluate();
            try {
                while (result.hasNext()) {
                    final BindingSet bindingSet = result.next();
                    System.err.println(bindingSet);
                }
            } finally {
                result.close();
            }

            return new String(stream.toByteArray());
        } catch (RepositoryException | MalformedQueryException |
                QueryEvaluationException | TupleQueryResultHandlerException e) {
            logger.error(query + "\n" + e.toString());
            throw new WrongQueryException(e.toString());
        } finally {
            if(repositoryConnection != null)
                repositoryConnection.close();
        }
    }

    @Override
    public Object getSearchResult(String platform) throws RepositoryException {
        RepositoryConnection repositoryConnection = null;
        try {
            repositoryConnection = repositoryComponent.getRepository().getConnection();

            final TupleQuery tupleQuery = repositoryConnection
                    .prepareTupleQuery(QueryLanguage.SPARQL,
                            queryPlatformSearch(platform));
            final TupleQueryResult result = tupleQuery.evaluate();

            FulltextSearchResponse fulltextSearchResponse = new FulltextSearchResponse(platform);
            try {
                while (result.hasNext()) {
                    fulltextSearchResponse.getSearch().add(getSearchItem(result.next(), platform));
                }
                fulltextSearchResponse.setSearchcontinue(fulltextSearchResponse.getSearch().size());
            } finally {
                result.close();
            }
            if(fulltextSearchResponse.getSearch().size() != 0){
                fulltextSearchResponse.setSuccess(1);
                return fulltextSearchResponse;
            } else {
                return new FulltextSearchNotFound(platform,1);
            }
        } catch (RepositoryException | MalformedQueryException |
                QueryEvaluationException e) {
            logger.error(e.toString());
            return new FulltextSearchNotFound(platform,0);
        } finally {
            if(repositoryConnection != null)
                repositoryConnection.close();
        }
    }

    private String queryPlatformSearch(String platform){
        return "PREFIX main: <http://www.semanticweb.org/rycht/ontologies/cyber_security_ontology#>\n" +
                "PREFIX platform: <http://www.semanticweb.org/rycht/ontologies/cyber_security_ontology/platform#>\n" +
                "PREFIX cve: <https://cve.mitre.org/about/terminology.html#>\n" +
                "PREFIX oval: <https://oval.mitre.org/language/version5.11/OVAL>\n" +
                "\n" +
                "SELECT ?cve ?title ?ovalLabel ?description\n" +
                "WHERE {\n" +
                "  ?cve a cve:CVE.\n" +
                "  ?cve main:hasTitle ?title.\n" +
                "  ?oval a oval:.\n" +
                "  ?oval main:hasCVE ?cve.\n" +
                "  ?oval main:affectedPlatform ?platform.\n" +
                "  ?platform rdfs:label ?platformLabel.\n" +
                "  FILTER (regex(?platformLabel, \""+ platform +"\", \"i\"))\n" +
                "  ?cve rdfs:label ?ovalLabel .\n" +
                "  ?oval main:hasDescription ?description\n" +
                "}";
    }

    private Search getSearchItem(final BindingSet bindingSet, String platform){
        return new Search.SearchBuilder()
                .setId(bindingSet.getValue("cve").stringValue())
                .setTitle(bindingSet.getValue("title").stringValue())
                .setPageId(1)
                .setUrl("//" + urlProject + "/entity?id=" + bindingSet.getValue("cve").stringValue())
                .setConceptUri("http://" + urlProject + "/entity?id=" + bindingSet.getValue("cve").stringValue())
                .setLabel(bindingSet.getValue("ovalLabel").stringValue())
                .setDescription(bindingSet.getValue("description").stringValue())
                .setMatch(new Match(platform))
                .build();
    }

    @Override
    public Object getEntityResult(String id, String props) throws RepositoryException {
        String[] ids = id.split("\\|");

        if(ids.length > 1){//multiple entity

        } else {
            if(props.equalsIgnoreCase("info")){//simple entity
                return getSimpleEntity(ids[0]);
            } else {//fat entity

            }
        }
        return null;
    }

    private Object getSimpleEntity(String id) throws RepositoryException {
        RepositoryConnection repositoryConnection = null;
        try {
            repositoryConnection = repositoryComponent.getRepository().getConnection();

            final TupleQuery tupleQuery = repositoryConnection
                    .prepareTupleQuery(QueryLanguage.SPARQL,
                            queryEntitySearch(id));
            final TupleQueryResult result = tupleQuery.evaluate();

            EntityObject entityObject = new EntityObject();
            entityObject.setType("item");
            try {
                while (result.hasNext()) {
                    final BindingSet bindingSet = result.next();
                    if(bindingSet.getValue("property").stringValue().contains("hasRefID")){
                        entityObject.setId( // dummy logic
                                id.contains(bindingSet.getValue("value").stringValue()) ?
                                        id : bindingSet.getValue("value").stringValue()
                        );
                    }
                    if(bindingSet.getValue("property").stringValue().contains("hasTitle")){
                        entityObject.setTitle( // dummy logic
                                id.contains(bindingSet.getValue("value").stringValue()) ?
                                        id : bindingSet.getValue("value").stringValue()
                        );
                    }
                }
            } finally {
                result.close();
            }
            Entity entity = new Entity();
            entity.setDetail(id, entityObject);

            SimpleEntity simpleEntity = new SimpleEntity();
            simpleEntity.setEntities(entity);

            return simpleEntity;
        } catch (RepositoryException | MalformedQueryException |
                QueryEvaluationException e) {
            logger.error(e.toString());
        } finally {
            if(repositoryConnection != null)
                repositoryConnection.close();
        }
        //here return missing entity
        MissingEntity missingEntity = new MissingEntity();
        missingEntity.setId(id);
        Entity entity = new Entity();
        entity.setDetail(id,missingEntity);
        SimpleEntity simpleEntity = new SimpleEntity();
        simpleEntity.setEntities(entity);
        return simpleEntity;
    }

    private String queryEntitySearch(String id){
        return "SELECT DISTINCT ?property ?value\n" +
                "WHERE {\n" +
                " <" + id + "> ?property ?value.\n" +
                "}";
    }

    @Override
    public boolean createCVE() throws RepositoryException {
        RepositoryConnection repositoryConnection = repositoryComponent.getRepository().getConnection();

        try {
            repositoryConnection.begin();

            ClassPathResource res = new ClassPathResource(ontologyResourcePath + "\\ontology.rdf");
            repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);

            //cve
            for(int i = 1999; i < 2022; i++){
                String year = String.valueOf(i);
                res = new ClassPathResource(ontologyResourcePath + "\\actual_cve\\cve_generate_data_" + year + ".rdf");
                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.TURTLE);
                repositoryConnection.commit();
            }
            //oval
            for (String ovalData : ovalDataList){
                res = new ClassPathResource(ontologyResourcePath + "\\actual_oval\\" + ovalData);
                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.TURTLE);
                repositoryConnection.commit();
            }
            return true;
        } catch (OpenRDFException | IOException ex) {
            repositoryConnection.rollback();
            logger.error(ex.toString());
            throw new WrongFormatException(ex.toString());
        } finally {
            repositoryConnection.close();
        }
    }

//    @Override
//    public boolean addUco2InDatabase(Boolean capec, Boolean cce, Boolean cve, Boolean cvss,
//                                   Boolean cybox, Boolean cyboxCommon, Boolean dataMarking,
//                                   Boolean IDSO, Boolean killchain, Boolean maec,
//                                   Boolean stix, Boolean stucco, Boolean uco2, Boolean OVAL) throws RepositoryException {
//        RepositoryConnection repositoryConnection = repositoryComponent.getRepository().getConnection();
//
//        try {
//            repositoryConnection.begin();
//            if(capec) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "Capec.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
////            if(cce) {
////                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "CCE.owl");
////                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
////            }
////            if(cve) {
////                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "CVE.owl");
////                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
////            }
////            if(cvss) {
////                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "CVSS.owl");
////                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
////            }
//            if(cybox) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "Cybox.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
//            if(cyboxCommon) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "CyboxCommon.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
//            if(dataMarking) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "DataMarking.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
////            if(IDSO) {
////                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "IDSOntologyv2.3.0.owl");
////                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
////            }
//            if(killchain) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "killchain.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
//            if(maec) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "Maec.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
//            if(stix) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "STIX.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
////            if(stucco) {
////                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "STUCCO.owl");
////                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
////            }
////            if(uco2) {
////                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "uco_2.owl");
////                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
////            }
////            if(OVAL) {
////                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "OVAL.owl");
////                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
////            }
//
//            repositoryConnection.commit();
//            return true;
//        } catch (OpenRDFException ex) {
//            repositoryConnection.rollback();
//            logger.error(ex.toString());
//            throw new WrongFormatException(ex.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            repositoryConnection.close();
//        }
//        return false;
//    }
}
