package com.acmecorp.trader.services;

import com.acmecorp.trader.config.TraderConfig;
import com.acmecorp.trader.domain.AuditRecord;
import com.acmecorp.trader.domain.TestObject;
import com.acmecorp.trader.repository.AuditTrailDAO;
import com.acmecorp.trader.repository.DbAuditTrailDAO;
import com.acmecorp.trader.repository.FileAuditTrailDAO;
import com.acmecorp.trader.repository.InMemoryAuditTrailDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by volen on 2017-07-31.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {InMemoryAuditTrailDAO.class, TestServiceImpl.class, TestServiceImplTest.Config.class})
public class TestServiceImplTest {
    @TestConfiguration
    static class Config {
        @Autowired
        AuditTrailDAO inMemoryAuditTrail;

        @Bean
        public AuditTrailDAO auditTrailDAO() {
            return inMemoryAuditTrail;
        }
    }

    @Autowired
    private TestService testService;

    @Autowired
    private AuditTrailDAO auditTrailDAO;


    @Test
    public void auditTrailCreatedWhenObjectSaved() throws Exception {
        testService.storeObject(new TestObject("my test"));

        List<AuditRecord> auditRecords = auditTrailDAO.getAllAuditRecords();

        assertTrue(auditRecords.size() == 1);
        assertTrue(auditRecords.get(0).getTimestamp() <= System.currentTimeMillis());
        assertTrue(auditRecords.get(0).getObjectId() == 1);
    }

}