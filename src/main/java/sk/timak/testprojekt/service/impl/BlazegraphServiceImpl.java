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
import sk.timak.testprojekt.service.BlazegraphService;

import java.io.*;

@Service
public class BlazegraphServiceImpl implements BlazegraphService {

    private static final Logger logger = LoggerFactory.getLogger(BlazegraphServiceImpl.class);

    @Value("${resource.path.uco2}")
    private String uco2ResourcePath;

    @Value("${resource.path.cve}")
    private String cveResourcePath;

    @Autowired
    private RepositoryComponent repositoryComponent;

    @Override
    public String getResult(String query) throws RepositoryException {
        RepositoryConnection repositoryConnection = null;
        try {
            repositoryConnection = repositoryComponent.getRepository().getConnection();

            final TupleQuery tupleQuery = repositoryConnection
                    .prepareTupleQuery(QueryLanguage.SPARQL,
                            query);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            tupleQuery.evaluate(new SPARQLResultsJSONWriter(stream));

            return new String(stream.toByteArray());
        } catch (RepositoryException | MalformedQueryException |
                QueryEvaluationException | TupleQueryResultHandlerException e) {
            logger.error(e.toString());
            throw new WrongQueryException(e.toString());
        } finally {
            if(repositoryConnection != null)
                repositoryConnection.close();
        }
    }

    @Override
    public boolean createCVE() throws RepositoryException {
        RepositoryConnection repositoryConnection = repositoryComponent.getRepository().getConnection();

        try {
            repositoryConnection.begin();

            ClassPathResource res = new ClassPathResource(cveResourcePath + "CVE.rdf");
            repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);

            for(int i = 1999; i < 2022; i++){
                String year = String.valueOf(i);
                res = new ClassPathResource(cveResourcePath + "data\\" + year + ".rdf");
                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.TURTLE);
            }

            repositoryConnection.commit();
            return true;
        } catch (OpenRDFException | IOException ex) {
            repositoryConnection.rollback();
            logger.error(ex.toString());
            throw new WrongFormatException(ex.toString());
        } finally {
            repositoryConnection.close();
        }
    }

    @Override
    public boolean addUco2InDatabase(Boolean capec, Boolean cce, Boolean cve, Boolean cvss,
                                   Boolean cybox, Boolean cyboxCommon, Boolean dataMarking,
                                   Boolean IDSO, Boolean killchain, Boolean maec,
                                   Boolean stix, Boolean stucco, Boolean uco2, Boolean OVAL) throws RepositoryException {
        RepositoryConnection repositoryConnection = repositoryComponent.getRepository().getConnection();

        try {
            repositoryConnection.begin();
            if(capec) {
                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "Capec.owl");
                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
            }
//            if(cce) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "CCE.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
//            if(cve) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "CVE.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
//            if(cvss) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "CVSS.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
            if(cybox) {
                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "Cybox.owl");
                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
            }
            if(cyboxCommon) {
                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "CyboxCommon.owl");
                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
            }
            if(dataMarking) {
                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "DataMarking.owl");
                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
            }
//            if(IDSO) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "IDSOntologyv2.3.0.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
            if(killchain) {
                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "killchain.owl");
                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
            }
            if(maec) {
                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "Maec.owl");
                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
            }
            if(stix) {
                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "STIX.owl");
                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
            }
//            if(stucco) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "STUCCO.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
//            if(uco2) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "uco_2.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }
//            if(OVAL) {
//                ClassPathResource res = new ClassPathResource(uco2ResourcePath + "OVAL.owl");
//                repositoryConnection.add(new File(res.getPath()), "base:", RDFFormat.RDFXML);
//            }

            repositoryConnection.commit();
            return true;
        } catch (OpenRDFException ex) {
            repositoryConnection.rollback();
            logger.error(ex.toString());
            throw new WrongFormatException(ex.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            repositoryConnection.close();
        }
        return false;
    }
}
