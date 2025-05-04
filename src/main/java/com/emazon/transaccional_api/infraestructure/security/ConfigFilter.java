package com.emazon.transaccional_api.infraestructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.emazon.transaccional_api.infraestructure.driving_http.util.ConstantsInfra;
import com.emazon.transaccional_api.infraestructure.security.jwt_configuration.JwtAutenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor

public class ConfigFilter {
    private final JwtAutenticationFilter jwtAuthFilter;
    private final CustomEntryPoint customEntryPoint;

     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
        .csrf(csrf -> csrf.disable()) 
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(ConstantsInfra.URL_SWAGGER1,ConstantsInfra.URL_SWAGGER2,ConstantsInfra.URL_SWAGGER3 ).permitAll()
            .anyRequest()
            .authenticated()    
             
         )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ) 
        .exceptionHandling(exceptionHandling -> 
                exceptionHandling.authenticationEntryPoint(customEntryPoint) 
            )
        .build();
    }
     
}
    
