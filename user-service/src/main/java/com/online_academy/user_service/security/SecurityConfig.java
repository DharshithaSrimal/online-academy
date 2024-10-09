package com.online_academy.user_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                //ALl the requests should be authenticted
                .authorizeHttpRequests(auth->{
                        auth.requestMatchers("/").permitAll();//public
                        auth.anyRequest().authenticated();})
                //Default login
                .formLogin(Customizer.withDefaults())
                //Provide oAuth2 Login
                .oauth2Login(Customizer.withDefaults())
                .build();
    }
}
