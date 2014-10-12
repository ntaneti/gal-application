package org.familysearch.gal.application.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.familysearch.engage.foundation.services.ServiceFactory;
import org.familysearch.engage.foundation.services.impl.DefaultServiceFactory;
import org.familysearch.engage.foundation.util.impl.FSLinkBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "")
@ComponentScan(basePackages = {"org.familysearch.gal.application.service.impl",
                               "org.familysearch.gal.application.rest.api.endpoints",
                               "org.familysearch.gal.application.rest.api.support"})
public abstract class BaseServiceConfig {
    @Bean
    public org.codehaus.jackson.map.ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public FSLinkBuilderFactory linkBuilderFactory() {
        return new FSLinkBuilderFactory();
    }

    @Bean
    ServiceFactory foundationServiceLocator() {
        final DefaultServiceFactory defaultServiceFactory = new DefaultServiceFactory();
        defaultServiceFactory.setFeaturesResourceLocation("config/local/features.properties");
        return defaultServiceFactory;
    }
}
