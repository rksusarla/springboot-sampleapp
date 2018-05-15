package com.acmecorp.sampleapp.config;

import com.acmecorp.sampleapp.repository.AuditTrailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;

/**
 * Created by volen on 2017-07-30.
 */
@Configuration
//@ImportResource("classpath:envConfig.xml")
public class TraderConfig {
    @Autowired
    private ApplicationContext ctx;

    @Bean
    public AuditTrailDAO auditTrailDAO() {
        return ctx.getBean("dbAuditTrailDAO", AuditTrailDAO.class);
//      return ctx.getBean("fileAuditTrailDAO", AuditTrailDAO.class);
    }

    @Bean
    public IdGenerator idGenerator() {
      return new JdkIdGenerator();
    }

}
