package com.acmecorp.trader.config;

import com.acmecorp.trader.domain.AuditRecord;
import com.acmecorp.trader.repository.AuditTrailDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by volen on 2017-07-30.
 */
@Configuration
public class TraderConfig {
    @Autowired
    ApplicationContext ctx;

    @Bean
    public AuditTrailDAO auditTrailDAO() {
        return ctx.getBean("dbAuditTrailDAO", AuditTrailDAO.class);
    }

}
