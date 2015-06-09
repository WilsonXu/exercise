package org.weber.prospringintegration.messageflow.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.expression.ReloadableResourceBundleExpressionSource;

/**
 * Created by wxu on 5/28/2015.
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public ReloadableResourceBundleExpressionSource filterRules() {
        ReloadableResourceBundleExpressionSource filterRules = new ReloadableResourceBundleExpressionSource();
        filterRules.setBasename("filter-rules");
        filterRules.setCacheSeconds(10);
        return filterRules;
    }
}
