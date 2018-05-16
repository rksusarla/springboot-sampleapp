package com.acmecorp.sampleapp.endpoints;

import com.acmecorp.sampleapp.domain.Message;
import com.acmecorp.sampleapp.domain.MessageAck;
import com.acmecorp.sampleapp.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by volen on 2017-07-28.
 */

@RestController
@RequestMapping("/messages/jms")
public class JmsMessageEndpoint {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    @Autowired
    private JmsMessagingTemplate jmt;

    @Autowired
    private IdGenerator idGenerator;


    @PostMapping
    public MessageAck recordMessage(@RequestBody Message msg, @RequestParam(name="delay", defaultValue = "0") int delay) {
        logger.info("Message received: "+msg);
        msg.setId(idGenerator.generateId());
        jmt.send("msgService", MessageBuilder.withPayload(msg).setHeader("delay", delay).build());
        return new MessageAck(msg, "received");
    }

    @GetMapping
    public Message getDeliveredMessage() {
        Message msg = jmt.receiveAndConvert("msgDelivered", Message.class);
        logger.info("Message delivered: "+msg);
        return msg;
    }

}
