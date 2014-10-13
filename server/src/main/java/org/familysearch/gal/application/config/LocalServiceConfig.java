package org.familysearch.gal.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("local")
@PropertySource("classpath:/config/local/config.properties")
public class LocalServiceConfig extends BaseServiceConfig {
}
