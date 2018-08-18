package com.acmecorp.sampleapp.services;

import com.acmecorp.sampleapp.domain.AuditRecord;
import com.acmecorp.sampleapp.domain.Operation;
import com.acmecorp.sampleapp.domain.Message;
import com.acmecorp.sampleapp.repository.AuditTrailDAO;
import com.acmecorp.sampleapp.repository.MessageDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.util.*;

/**
 * Created by volen on 2017-07-28.
 */
@Service
public class MessageServiceImpl implements MessageService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuditTrailDAO auditTrailDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private IdGenerator idGenerator;


    @Override
    public Message storeMessage(Message message, int delay) {
        logger.info("Storing message: "+message);
        if (delay > 0) {
            logger.info("Applying artificial delay of " + delay + " seconds");
            try {
                Thread.sleep(delay * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        message.setId(idGenerator.generateId());
        message.setTimestamp(System.currentTimeMillis());
        messageDAO.storeMessage(message);
        auditTrailDAO.saveAuditRecord(new AuditRecord(message.getId(), Operation.CREATE));
        return message;
    }

    @Override
    public Message retrieveMessage(UUID id) {
        Message msg = messageDAO.findMessageById(id);
        auditTrailDAO.saveAuditRecord(new AuditRecord(id, Operation.READ));
        return msg;
    }

    @Override
    public void deleteMessage(UUID id) {
        messageDAO.deleteMessage(id);
        auditTrailDAO.saveAuditRecord(new AuditRecord(id, Operation.DELETE));
    }

    @Override
    public Message updateObject(Message message) {
        message.setTimestamp(System.currentTimeMillis());
        messageDAO.updateMessage(message);
        auditTrailDAO.saveAuditRecord(new AuditRecord(message.getId(), Operation.UPDATE));
        return message;
    }

    @Override
    public List<Message> listMessages() {
        List<Message> messages = messageDAO.getAllMessages();
        for (Message message: messages) {
            auditTrailDAO.saveAuditRecord(new AuditRecord(message.getId(), Operation.READ));
        }
        return messages;
    }

}
