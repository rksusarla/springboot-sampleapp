package com.acmecorp.sampleapp.repository;

import com.acmecorp.sampleapp.domain.AuditRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by volen on 2017-08-01.
 */
@Repository
public class InMemoryAuditTrailDAO implements AuditTrailDAO {
    private Logger logger = LoggerFactory.getLogger(InMemoryAuditTrailDAO.class);
    private List<AuditRecord> auditRecords = new ArrayList<>();

    @Override
    public List<AuditRecord> getAllAuditRecords() {
        return Collections.unmodifiableList(auditRecords);
    }

    @Override
    public void saveAuditRecord(AuditRecord record) {
        auditRecords.add(record);
    }
}
