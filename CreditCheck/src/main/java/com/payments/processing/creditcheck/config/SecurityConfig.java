package com.payments.processing.creditcheck.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("default").
                password(passwordEncoder().
                        encode("password")).
                roles("USER","ADMIN");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/setup/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic().and().csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
