package sk.timak.testprojekt.service.impl;

import org.openrdf.OpenRDFException;
import org.openrdf.query.*;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlazegraphServiceImpl implements BlazegraphService {

    private static final Logger logger = LoggerFactory.getLogger(BlazegraphServiceImpl.class);

    @Value("${uco2.resource.path}")
    private String uco2ResourcePath;

    @Autowired
    private RepositoryComponent repositoryComponent;

    @Override
    public List<String> getResult(String query) throws RepositoryException {
        List<String> answer = null;
        RepositoryConnection repositoryConnection = null;
        try {
            repositoryConnection = repositoryComponent.getRepository().getConnection();

            final TupleQuery tupleQuery = repositoryConnection
                    .prepareTupleQuery(QueryLanguage.SPARQL,
                            query);
            TupleQueryResult result = tupleQuery.evaluate();
            try {
                answer = new ArrayList<>();
                while (result.hasNext()) {
                    BindingSet bindingSet = result.next();
                    answer.add(bindingSet.toString().substring(1,bindingSet.toString().length()-1));
                }
            } finally {
                result.close();
            }
        } catch (RepositoryException | MalformedQueryException | QueryEvaluationException e) {
            logger.error(e.toString());
            throw new WrongQueryException(e.toString());
        } finally {
            if(repositoryConnection != null)
                repositoryConnection.close();
        }
        return answer;
    }

    @Override
    public void addOWLInDatabase(Boolean capec, Boolean cce, Boolean cve, Boolean cvss,
                                   Boolean cybox, Boolean cyboxCommon, Boolean dataMarking,
                                   Boolean IDSO, Boolean killchain, Boolean maec,
                                   Boolean stix, Boolean stucco, Boolean uco2) throws RepositoryException, RDFParseException {
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

            repositoryConnection.commit();
        } catch (OpenRDFException ex) {
            repositoryConnection.rollback();
            logger.error(ex.toString());
            throw new WrongFormatException(ex.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            repositoryConnection.close();
        }
    }
}
