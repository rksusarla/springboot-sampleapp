package com.acmecorp.trader.repository;

import com.acmecorp.trader.domain.Message;

import java.util.List;
import java.util.UUID;

public interface MessageDAO {
    void storeMessage(Message message);

    Message findMessageById(UUID id);

    void deleteMessage(UUID id);

    Message updateMessage(Message message);

    List<Message> getAllMessages();
}
