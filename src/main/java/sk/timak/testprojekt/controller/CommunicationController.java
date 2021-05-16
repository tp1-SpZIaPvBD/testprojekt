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

    @GetMapping("/search")
    public ResponseEntity<?> getSearchResults(@RequestParam String platform) throws RepositoryException {
        return ResponseEntity.ok(blazegraphService.getSearchResult(platform));
    }

    @GetMapping("/entity")// do not use it
    public ResponseEntity<?> getEntityResults(@RequestParam(required = true) String id,
                                              @RequestParam(required = false) String props,
                                              @RequestParam(required = false) String languages,
                                              @RequestParam(required = false) Boolean languagefallback) throws RepositoryException {
        return ResponseEntity.ok(blazegraphService.getEntityResult(id, props));
    }

    @PostMapping("/create/ontology")
    public ResponseEntity<?> createCVEAndImportCVE() throws RDFParseException, RepositoryException {
        if(blazegraphService.createCVE())
            return ResponseEntity.ok("SUCCESS");
        else
            return ResponseEntity.ok("FAILURE");
    }

//    @PostMapping("/create/uco2/")
//    public ResponseEntity<?> createUco2InDatabase(@RequestParam(required = false, defaultValue = "true") Boolean capec,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean cce,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean cve,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean cvss,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean cybox,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean cyboxCommon,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean dataMarking,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean IDSO,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean killchain,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean maec,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean stix,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean stucco,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean uco2,
//                                                 @RequestParam(required = false, defaultValue = "true") Boolean OVAL) throws RDFParseException, RepositoryException {
//        boolean result = blazegraphService.addUco2InDatabase(capec, cce, cve, cvss, cybox, cyboxCommon,
//                dataMarking, IDSO, killchain, maec, stix, stucco, uco2, OVAL);
//        if(result)
//            return ResponseEntity.ok("SUCCESS");
//        else
//            return ResponseEntity.ok("FAILURE");
//    }
}
