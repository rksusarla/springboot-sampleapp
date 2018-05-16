package com.acmecorp.sampleapp.endpoints;

import com.acmecorp.sampleapp.domain.Message;
import com.acmecorp.sampleapp.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by volen on 2017-07-28.
 */

@RestController
@RequestMapping("/messages")
public class MessageEndpoint {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/{id}")
    public Message getMessage(@PathVariable UUID id) {
        return messageService.retrieveMessage(id);
    }

    @PostMapping
    public Message recordMessage(@RequestBody Message msg, @RequestParam(name = "delay", defaultValue = "0") int delay) {
        logger.info("Message received: "+msg);
        return messageService.storeMessage(msg, delay);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMessage(@PathVariable UUID id) {
        messageService.deleteMessage(id);
    }

    @PutMapping(value = "/{id}")
    public Message updateMessage(@PathVariable UUID id, @RequestBody Message msg) {
        msg.setId(id);
        return messageService.updateObject(msg);
    }

    @GetMapping
    public List<Message> listAllMessages() {
        return messageService.listMessages();
    }

}
