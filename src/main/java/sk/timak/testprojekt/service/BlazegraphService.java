package sk.timak.testprojekt.service;

import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFParseException;

import java.util.List;

public interface BlazegraphService {

    List<String> getResult(String query) throws RepositoryException;
    void addOWLInDatabase(Boolean capec, Boolean cce, Boolean cve, Boolean cvss,
                          Boolean cybox, Boolean cyboxCommon, Boolean dataMarking,
                          Boolean IDSO, Boolean killchain, Boolean maec,
                          Boolean stix, Boolean stucco, Boolean uco2) throws RepositoryException, RDFParseException;

}
