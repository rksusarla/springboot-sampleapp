package com.acmecorp.sampleapp.services;

import com.acmecorp.sampleapp.domain.Message;

import java.util.List;
import java.util.UUID;

/**
 * Created by volen on 2017-07-28.
 */
public interface MessageService {
    Message storeMessage(Message message);
    Message retrieveMessage(UUID uuid);
    void deleteMessage(UUID id);
    Message updateObject(Message message);
    List<Message> listMessages();
}
