package org.wilson.springsecurity.kaptcha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public AuthenticationProvider kaptchaAuthenticationProvider() {
        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager(
                User.builder().username("wilson").password("{noop}123").roles("admin").build());
        KaptchaAuthenticationProvider provider = new KaptchaAuthenticationProvider();
        provider.setUserDetailsService(users);
        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(this.kaptchaAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/vc.jpg").permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage("/mylogin.html").loginProcessingUrl("/doLogin").defaultSuccessUrl("/index.html")
                .failureForwardUrl("/mylogin.html").usernameParameter("uname").passwordParameter("passwd").permitAll()
                .and().csrf().disable();
    }
}
