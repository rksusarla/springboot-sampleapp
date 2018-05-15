package com.acmecorp.sampleapp.domain;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by volen on 2017-07-28.
 */
public class Message {
    private String text;
    private String fromUser;
    private String toUser;
    private UUID id;
    private long timestamp;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id.equals(message.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    //=====
//    private static int COUNTER = 1;
//    private synchronized int NEXT_ID() {
//        return COUNTER++;
//    }
}
