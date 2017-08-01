package com.acmecorp.trader.domain;

import java.sql.Timestamp;

/**
 * Created by volen on 2017-07-30.
 */
public class AuditRecord {
    private final long       timestamp;
    private final int        objectId;
    private final Operation  operation;

    public AuditRecord(int objectId, Operation operation) {
        this.operation = operation;
        this.timestamp = System.currentTimeMillis();
        this.objectId = objectId;
    }

    public AuditRecord(int objectId, Operation operation, long timestamp) {
        this.operation = operation;
        this.timestamp = timestamp;
        this.objectId = objectId;
    }

    public Operation getOperation() {
        return operation;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "AuditRecord{" +
                "timestamp=" + timestamp +
                ", objectId=" + objectId +
                ", operation=" + operation +
                '}';
    }
}
