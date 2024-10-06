package com.example.jwttoken.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.example.jwttoken.jwtSecurity.JwtAuthenEntryPoint;
import com.example.jwttoken.jwtSecurity.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired PasswordEncoder passwordEncoder;

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
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/auth/login").permitAll().anyRequest().authenticated())
                        
                    .exceptionHandling(e->e.authenticationEntryPoint(authenEntryPoint))
                    .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))    
                ;
        httpSecurity.addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class);

        return  httpSecurity.build();
    }

    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
        
    }

}
