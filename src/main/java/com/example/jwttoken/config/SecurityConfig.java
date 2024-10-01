package com.example.jwttoken.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwttoken.jwtSecurity.JwtAuthFilter;
import com.example.jwttoken.jwtSecurity.JwtAuthenEntryPoint;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenEntryPoint authenEntryPoint;

    @Autowired
    private JwtAuthFilter authFilter;

    public <SecurityFilterChain> SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

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

        return (SecurityFilterChain) httpSecurity.build();
    }

}
