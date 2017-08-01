package com.acmecorp.trader.services;

import com.acmecorp.trader.domain.TestObject;

import java.util.List;

/**
 * Created by volen on 2017-07-28.
 */
public interface TestService {
    TestObject storeObject(TestObject obj);
    TestObject retrieveObject(int uuid);
    TestObject deleteObject(int id);
    TestObject updateObject(int id, TestObject obj);
    List<TestObject> listObjects();
}
