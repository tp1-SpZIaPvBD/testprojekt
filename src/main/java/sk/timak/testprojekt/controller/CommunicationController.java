package sk.timak.testprojekt.controller;

import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.timak.testprojekt.service.BlazegraphService;

@RestController
public class CommunicationController {

    private static final Logger logger = LoggerFactory.getLogger(CommunicationController.class);

    @Autowired
    private BlazegraphService blazegraphService;

    @GetMapping("/query")
    public ResponseEntity<?> getQueryResults(@RequestParam String query) throws RepositoryException {
        return ResponseEntity.ok(blazegraphService.getResult(query));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOWLInDatabase(@RequestParam(required = true) Boolean capec,
                                                 @RequestParam(required = false) Boolean cce,
                                                 @RequestParam(required = false) Boolean cve,
                                                 @RequestParam(required = false) Boolean cvss,
                                                 @RequestParam(required = true) Boolean cybox,
                                                 @RequestParam(required = true) Boolean cyboxCommon,
                                                 @RequestParam(required = true) Boolean dataMarking,
                                                 @RequestParam(required = false) Boolean IDSO,
                                                 @RequestParam(required = true) Boolean killchain,
                                                 @RequestParam(required = true) Boolean maec,
                                                 @RequestParam(required = true) Boolean stix,
                                                 @RequestParam(required = false) Boolean stucco,
                                                 @RequestParam(required = false) Boolean uco2) throws RDFParseException, RepositoryException {
        blazegraphService.addOWLInDatabase(capec, cce, cve, cvss, cybox, cyboxCommon,
                dataMarking, IDSO, killchain, maec, stix, stucco, uco2);
        return ResponseEntity.ok("SUCCESS.");
    }
}
