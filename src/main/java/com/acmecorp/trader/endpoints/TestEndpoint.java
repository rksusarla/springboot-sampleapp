package com.acmecorp.trader.endpoints;

import com.acmecorp.trader.domain.AuditRecord;
import com.acmecorp.trader.domain.TestObject;
import com.acmecorp.trader.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by volen on 2017-07-28.
 */

@RestController
@RequestMapping("/testobjects")
public class TestEndpoint {
    @Autowired
    TestService testService;

    @GetMapping(value = "/{id}")
    public TestObject getTestObject(@PathVariable int id) {
        return testService.retrieveObject(id);
    }

    @PostMapping
    public TestObject storeTestObject(@RequestBody TestObject obj) {
        return testService.storeObject(obj);
    }

    @DeleteMapping(value = "/{id}")
    public TestObject deleteTestObject(@PathVariable int id) {
        return testService.deleteObject(id);
    }

    @PutMapping(value = "/{id}")
    public TestObject updateTestObject(@PathVariable int id, @RequestBody TestObject obj) {
        return testService.updateObject(id, obj);
    }

    @GetMapping
    public List<TestObject> listTestObjects() {
        return testService.listObjects();
    }

}
