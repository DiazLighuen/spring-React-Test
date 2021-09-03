package com.galeno.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
Configuration of the transaction manager
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.galeno",
        repositoryFactoryBeanClass = EntityGraphJpaRepositoryBean.class
)
@EnableTransactionManagement
public class PersistenceConfiguration {
}
