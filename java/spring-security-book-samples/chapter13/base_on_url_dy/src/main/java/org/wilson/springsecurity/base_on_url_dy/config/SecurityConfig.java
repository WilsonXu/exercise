package org.wilson.springsecurity.base_on_url_dy.config;

import org.wilson.springsecurity.base_on_url_dy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(new UrlAuthorizationConfigurer<>(http.getSharedObject(ApplicationContext.class)))
                        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                                object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                                object.setRejectPublicInvocations(true);
                                return object;
                            }
                        });
        http.formLogin().and().csrf().disable();
    }

    @Autowired
    public void setCustomFilterInvocationSecurityMetadataSource(CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource) {
        this.customFilterInvocationSecurityMetadataSource = customFilterInvocationSecurityMetadataSource;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
