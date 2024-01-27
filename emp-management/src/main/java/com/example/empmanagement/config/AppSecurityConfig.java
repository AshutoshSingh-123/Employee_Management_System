package com.example.empmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails u1= User.builder()
                .username("ashu")
                .password("{noop}ashu")
                .roles("ADMIN","USER")
                .build();
        UserDetails u2= User.builder()
                .username("ashu1")
                .password("{noop}ashu1")
                .roles("USER")
                .build();
        UserDetails u3= User.builder()
                .username("ashu2")
                .password("{noop}ashu2")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(u1,u2,u3);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers("/login.css").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/processLogin")
                                .permitAll()
                );
        return http.build();

    }
}
