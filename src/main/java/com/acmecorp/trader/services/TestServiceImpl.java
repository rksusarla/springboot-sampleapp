package com.acmecorp.trader.services;

import com.acmecorp.trader.domain.AuditRecord;
import com.acmecorp.trader.domain.Operation;
import com.acmecorp.trader.domain.TestObject;
import com.acmecorp.trader.repository.AuditTrailDAO;
import com.acmecorp.trader.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by volen on 2017-07-28.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    @Qualifier("auditTrailDAO")
    private AuditTrailDAO auditTrailDAO;

    private Map<Integer, TestObject> testObjects = new HashMap<>();

    @Override
    public TestObject storeObject(TestObject obj) {
        testObjects.put(obj.getId(), obj);
        auditTrailDAO.saveAuditRecord(new AuditRecord(obj.getId(), Operation.CREATE));
        return obj;
    }

    @Override
    public TestObject retrieveObject(int id) {
        auditTrailDAO.saveAuditRecord(new AuditRecord(id, Operation.READ));
        return testObjects.get(id);
    }

    @Override
    public TestObject deleteObject(int id) {
        auditTrailDAO.saveAuditRecord(new AuditRecord(id, Operation.DELETE));
        return testObjects.remove(id);
    }

    @Override
    public TestObject updateObject(int id, TestObject obj) {
        auditTrailDAO.saveAuditRecord(new AuditRecord(id, Operation.UPDATE));
        return testObjects.put(id, obj);
    }


    public List<TestObject> listObjects() {
        List<TestObject> objs = new ArrayList<TestObject>(testObjects.values());
        for (TestObject obj: objs) {
            auditTrailDAO.saveAuditRecord(new AuditRecord(obj.getId(), Operation.READ));
        }
        return objs;
    }

    public void setAuditTrailDAO(AuditTrailDAO auditTrailDAO) {
        this.auditTrailDAO = auditTrailDAO;
    }
}
