package com.acmecorp.sampleapp.domain;

public class MessageAck {
    private Message message;
    private String  status;

    public MessageAck(){}

    public MessageAck(Message message, String status) {
        this.message = message;
        this.status = status;
    }

    public Message getMessage() {
        return message;
    }



    public String getStatus() {
        return status;
    }


}
