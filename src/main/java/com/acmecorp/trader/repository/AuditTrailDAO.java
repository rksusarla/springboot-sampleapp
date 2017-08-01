package com.acmecorp.trader.repository;

import com.acmecorp.trader.domain.AuditRecord;

import java.util.List;

/**
 * Created by volen on 2017-07-28.
 */

public interface AuditTrailDAO {
    List<AuditRecord> getAllAuditRecords();
    void saveAuditRecord(AuditRecord record);
}
