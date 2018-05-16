package com.acmecorp.sampleapp.services;

import com.acmecorp.sampleapp.domain.Message;

import java.util.List;
import java.util.UUID;

/**
 * Created by volen on 2017-07-28.
 */
public interface MessageService {
    default Message storeMessage(Message message) { return storeMessage(message, 0); }
    Message storeMessage(Message message, int delay);
    Message retrieveMessage(UUID uuid);
    void deleteMessage(UUID id);
    Message updateObject(Message message);
    List<Message> listMessages();
}
