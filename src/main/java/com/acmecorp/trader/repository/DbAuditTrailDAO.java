package com.acmecorp.trader.repository;

import com.acmecorp.trader.domain.AuditRecord;
import com.acmecorp.trader.domain.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by volen on 2017-07-28.
 */
@Repository
public class DbAuditTrailDAO implements AuditTrailDAO {
    private Logger logger = LoggerFactory.getLogger(DbAuditTrailDAO.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DbAuditTrailDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAuditRecord(AuditRecord record) {
        logger.info("Saving record {} to DB", record);
        jdbcTemplate.update("insert into AUDIT(RECORD_ID, OPERATION, AUDIT_TIMESTAMP) "+
                            "values (?,?,?)", record.getRecordId(), record.getOperation().toString(), record.getTimestamp());
    }

    @Transactional
    public List<AuditRecord> getAllAuditRecords() {
        logger.info("Loading all records from DB");
        return jdbcTemplate.query("select * from AUDIT order by AUDIT_TIMESTAMP", new RowMapper<AuditRecord>() {
            @Override
            public AuditRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new AuditRecord(rs.getObject("RECORD_ID", UUID.class),
                                       Operation.valueOf(rs.getString("OPERATION")),
                                       rs.getLong("AUDIT_TIMESTAMP"));
            }
        });
    }

}
