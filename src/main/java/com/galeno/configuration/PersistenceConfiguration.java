package com.galeno.configuration;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
Configuration of the transaction manager
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.galeno",
        repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class
)
@EnableTransactionManagement
public class PersistenceConfiguration {
}
