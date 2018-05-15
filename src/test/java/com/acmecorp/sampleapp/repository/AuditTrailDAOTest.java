package com.acmecorp.sampleapp.repository;

import com.acmecorp.sampleapp.domain.AuditRecord;
import com.acmecorp.sampleapp.domain.Message;

import com.acmecorp.sampleapp.services.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SimpleIdGenerator;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

/**
 * Created by volen on 2017-07-31.
 */
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {InMemoryAuditTrailDAO.class, MessageServiceImpl.class, AuditTrailDAOTest.Config.class})
@SpringBootTest(webEnvironment=NONE)
@ContextConfiguration
public class AuditTrailDAOTest {
    @TestConfiguration
    static class Config {
//        @Resource(name="inMemoryAuditTrailDAO")
    	@Autowired
    	@Qualifier("inMemoryAuditTrailDAO")
        AuditTrailDAO inMemoryAuditTrail;

        @Bean
        public AuditTrailDAO auditTrailDAO() {
            return inMemoryAuditTrail;
        }
    }

    @Autowired
    private MessageService messageService;

    @Autowired
    @Qualifier("auditTrailDAO")
    private AuditTrailDAO auditTrailDAO;

    @Test
    public void auditTrailCreatedWhenObjectSaved() throws Exception {
        Message msg = new Message();
        msg.setTimestamp(System.currentTimeMillis());
        msg.setToUser("vlad");
        msg.setFromUser("dima");
        msg.setId(UUID.randomUUID());
        msg.setText("test text");
        messageService.storeMessage(msg);

        List<AuditRecord> auditRecords = auditTrailDAO.getAllAuditRecords();

        assertTrue(auditRecords.size() == 1);
        assertTrue(auditRecords.get(0).getTimestamp() <= System.currentTimeMillis());
        assertEquals((new SimpleIdGenerator()).generateId(), auditRecords.get(0).getRecordId());
    }

}