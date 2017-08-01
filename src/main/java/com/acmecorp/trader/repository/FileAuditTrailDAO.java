package com.acmecorp.trader.repository;

import com.acmecorp.trader.domain.AuditRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by volen on 2017-07-30.
 */
@Repository
@PropertySource("classpath:trader.properties")
public class FileAuditTrailDAO implements AuditTrailDAO {
    private Logger logger = LoggerFactory.getLogger(FileAuditTrailDAO.class);

    @Value("${auditTrail.filename}")
    private String filename;


    public FileAuditTrailDAO() {
        logger.info("Initializing logger to store audit entries to file {}", filename);
    }

    @Override
    public List<AuditRecord> getAllAuditRecords() {
        // open file in readonly mode, read all records into a list
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void saveAuditRecord(AuditRecord record) {
        // open file for writing in 'append' mode
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // util method, required to inject properties from config file
    @Bean
    public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
