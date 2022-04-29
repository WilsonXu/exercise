package org.wilson.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Primary
    public UserDetailsService us1() {
        return new InMemoryUserDetailsManager(User.builder().username("wilson").password("{noop}123").roles("admin").build());
    }

    @Bean
    public UserDetailsService us2() {
        return new InMemoryUserDetailsManager(User.builder().username("sang").password("{noop}123").roles("user").build());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() {
        DaoAuthenticationProvider provider1 = new DaoAuthenticationProvider();
        provider1.setUserDetailsService(us1());
        DaoAuthenticationProvider provider2 = new DaoAuthenticationProvider();
        provider2.setUserDetailsService(us2());
        return new ProviderManager(provider1, provider2);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginPage("/login.html").loginProcessingUrl("/doLogin").defaultSuccessUrl("/index.html")
                .usernameParameter("uname").passwordParameter("passwd").permitAll()
                .and().csrf().disable();
    }
}
