package org.wilson.springsecurity.formlogin;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests.anyRequest().authenticated())
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
//                .defaultSuccessUrl("/index.html")
//                .successForwardUrl("/index")
                .successHandler(new MyAuthenticationSuccessHandler())
                .usernameParameter("uname")
                .passwordParameter("passwd")
                .permitAll()
                .and()
                .csrf().disable();
    }
}
