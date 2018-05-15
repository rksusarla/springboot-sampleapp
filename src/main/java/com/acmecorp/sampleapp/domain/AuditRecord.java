package com.acmecorp.sampleapp.domain;

import java.util.UUID;

/**
 * Created by volen on 2017-07-30.
 */
public class AuditRecord {
    private final long       timestamp;
    private final UUID recordId;
    private final Operation  operation;

    public AuditRecord(UUID recordId, Operation operation) {
        this.operation = operation;
        this.timestamp = System.currentTimeMillis();
        this.recordId = recordId;
    }

    public AuditRecord(UUID recordId, Operation operation, long timestamp) {
        this.operation = operation;
        this.timestamp = timestamp;
        this.recordId = recordId;
    }

    public Operation getOperation() {
        return operation;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public UUID getRecordId() {
        return recordId;
    }

    @Override
    public String toString() {
        return "AuditRecord{" +
                "timestamp=" + timestamp +
                ", recordId=" + recordId +
                ", operation=" + operation +
                '}';
    }
}
