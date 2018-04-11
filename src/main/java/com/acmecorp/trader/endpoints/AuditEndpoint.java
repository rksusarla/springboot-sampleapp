package com.acmecorp.trader.endpoints;


import com.acmecorp.trader.domain.AuditRecord;
import com.acmecorp.trader.repository.AuditTrailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by volen on 2017-07-31.
 */
@RestController
@RequestMapping(value = "/audit")
public class AuditEndpoint {
    @Autowired
    @Qualifier("auditTrailDAO")
    AuditTrailDAO auditTrailDAO;

    @GetMapping(value = "/{id}")
    public List<AuditRecord> getAuditRecord(@PathVariable int id) {
        throw new UnsupportedOperationException("Retrieval of audit records by ID is not yet supported");
    }

    @GetMapping
    public List<AuditRecord> listAuditRecords() {
        return auditTrailDAO.getAllAuditRecords();
    }

}
