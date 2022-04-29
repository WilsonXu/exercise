package org.wilson.springsecurity.objectpostprocessor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin().withObjectPostProcessor(new ObjectPostProcessor<UsernamePasswordAuthenticationFilter>() {
                    @Override
                    public <O extends UsernamePasswordAuthenticationFilter> O postProcess(O object) {
                        object.setUsernameParameter("name");
                        object.setPasswordParameter("passwd");
                        object.setAuthenticationSuccessHandler((req, resp, auth) -> {
                            resp.getWriter().write("login success");
                        });
                        return object;
                    }
                })
                .and().csrf().disable();
    }
}
