package com.acmecorp.trader.domain;

/**
 * Created by volen on 2017-07-28.
 */
public class TestObject {
    private String text;
    private int id;

    public TestObject() {
        id = NEXT_ID();
    }

    public TestObject(String text) {
        this();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    //=====
    private static int COUNTER = 1;
    private synchronized int NEXT_ID() {
        return COUNTER++;
    }
}
