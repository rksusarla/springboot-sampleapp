package com.acmecorp.sampleapp.repository;

import com.acmecorp.sampleapp.domain.AuditRecord;

import java.util.List;

/**
 * Created by volen on 2017-07-28.
 */

public interface AuditTrailDAO {
    List<AuditRecord> getAllAuditRecords();
    void saveAuditRecord(AuditRecord record);
}
