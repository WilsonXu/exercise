package org.weber.prospringintegration.messageflow.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.expression.ReloadableResourceBundleExpressionSource;

/**
 * Created by wxu on 5/25/2015.
 */
@Configuration
public class RouterConfiguration {
    @Bean
    public ReloadableResourceBundleExpressionSource reloadableRouteExpressions() {
        ReloadableResourceBundleExpressionSource reloadableResourceBundleExpressionSource = new ReloadableResourceBundleExpressionSource();
        reloadableResourceBundleExpressionSource.setBasename("router-expressions");
        reloadableResourceBundleExpressionSource.setCacheSeconds(10);
        return reloadableResourceBundleExpressionSource;
    }
}
