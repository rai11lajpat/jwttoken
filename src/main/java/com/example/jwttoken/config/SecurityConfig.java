package com.example.jwttoken.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.example.jwttoken.jwtSecurity.JwtAuthenEntryPoint;
import com.example.jwttoken.jwtSecurity.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenEntryPoint authenEntryPoint;

    @Autowired
    private JwtAuthenticationFilter authFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home/**")
                        .authenticated()
                        .requestMatchers("/auth/login").permitAll().anyRequest().authenticated())
                        
                    .exceptionHandling(e->e.authenticationEntryPoint(authenEntryPoint))
                    .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))    
                ;
        httpSecurity.addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class);

        return  httpSecurity.build();
    }

}
