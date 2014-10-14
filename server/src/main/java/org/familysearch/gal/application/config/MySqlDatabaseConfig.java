package org.familysearch.gal.application.config;

import com.googlecode.flyway.core.Flyway;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@Profile("mysql")
public class MySqlDatabaseConfig extends BaseDatabaseConfig {

    @Bean
    public Flyway flyway() {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setLocations("classpath:flyway/migration/appgallery/mysql");
        flyway.migrate();
        return flyway;
    }

    /** JPA configuration */
    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public org.apache.commons.dbcp.BasicDataSource dataSource() {
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
