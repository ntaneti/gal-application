package org.familysearch.gal.application.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("postgres")
public class PostgresDatabaseConfig extends BaseDatabaseConfig{

    @Bean
    public  org.apache.commons.dbcp.BasicDataSource dataSource() {
        final BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
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
