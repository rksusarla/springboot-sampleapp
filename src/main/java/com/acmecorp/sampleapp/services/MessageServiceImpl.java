package com.acmecorp.sampleapp.services;

import com.acmecorp.sampleapp.domain.AuditRecord;
import com.acmecorp.sampleapp.domain.Operation;
import com.acmecorp.sampleapp.domain.Message;
import com.acmecorp.sampleapp.repository.AuditTrailDAO;
import com.acmecorp.sampleapp.repository.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.util.*;

/**
 * Created by volen on 2017-07-28.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private AuditTrailDAO auditTrailDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private IdGenerator idGenerator;


    @Override
    public Message storeMessage(Message message) {
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


    public List<Message> listMessages() {
        List<Message> messages = messageDAO.getAllMessages();
        for (Message message: messages) {
            auditTrailDAO.saveAuditRecord(new AuditRecord(message.getId(), Operation.READ));
        }
        return messages;
    }

}
