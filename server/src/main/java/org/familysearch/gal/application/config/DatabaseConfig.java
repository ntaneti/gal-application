package org.familysearch.gal.application.config;

import com.googlecode.flyway.core.Flyway;
import org.apache.commons.dbcp.BasicDataSource;
import org.familysearch.gal.application.dal.api.base.ValidatingHibernateJPADialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"org.familysearch.gal.application.dal.**"})
@EnableJpaRepositories( basePackages = "org.familysearch.gal.application.dal.api",
                        entityManagerFactoryRef = "entityManagerFactory",
                        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class DatabaseConfig {
    /**
     * Translates Hibernate exceptions to Spring exceptions for @Repository (DAO) classes  
     */

    @Autowired
    Environment env;

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor(); 
    }

    /**Flyway Configuration*/
    
    @Bean
    Flyway flyway() {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setLocations("classpath:flyway/migration/appgallery");
        flyway.migrate();
        return flyway;
    }

    /**JPA configuration */
    @Bean
    org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter jpaVendorAdapter()
    {
        final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();

        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
//        hibernateJpaVendorAdapter.setDatabase(mysql());
        
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    ValidatingHibernateJPADialect paDialect() {
        return new ValidatingHibernateJPADialect();
    }

    /**EntityManager factory */
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaDialect(jpaDialect());
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("appGalleryPU");
        return localContainerEntityManagerFactoryBean;
    }

    /**JPA Dialect */
    @Bean ValidatingHibernateJPADialect jpaDialect() {
        ValidatingHibernateJPADialect validatingHibernateJPADialect = new ValidatingHibernateJPADialect();
        return validatingHibernateJPADialect;
    }

    /**TransactionManager */
    @Bean
    PlatformTransactionManager transactionManager() {
        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        jpaTransactionManager.setJpaDialect(jpaDialect());
        return jpaTransactionManager;
    }

    @Bean org.apache.commons.dbcp.BasicDataSource dataSource() {
        final BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl(env.getProperty("gallery.db.url"));
        basicDataSource.setUsername(env.getProperty("gallery.db.username"));
        basicDataSource.setPassword(env.getProperty("gallery.db.password"));
        basicDataSource.setInitialSize(40);
        basicDataSource.setMaxActive(100);
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setTestOnBorrow(true);
        return basicDataSource;
    }
}
