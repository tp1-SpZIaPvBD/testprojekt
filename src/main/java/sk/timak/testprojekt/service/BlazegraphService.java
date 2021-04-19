package sk.timak.testprojekt.service;

import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFParseException;

public interface BlazegraphService {

    String getResult(String query) throws RepositoryException;
    boolean addUco2InDatabase(Boolean capec, Boolean cce, Boolean cve, Boolean cvss,
                          Boolean cybox, Boolean cyboxCommon, Boolean dataMarking,
                          Boolean IDSO, Boolean killchain, Boolean maec,
                          Boolean stix, Boolean stucco, Boolean uco2, Boolean OVAL) throws RepositoryException, RDFParseException;
    boolean createCVE() throws RepositoryException, RDFParseException;
}
