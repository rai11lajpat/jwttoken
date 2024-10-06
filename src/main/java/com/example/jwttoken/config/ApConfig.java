package com.example.jwttoken.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ApConfig {
    
   

    // @Bean
    // public UserDetailsService userDetailsService(){

    //     UserDetails user1= User.builder().username("lajpat rai").password(passwordEncoder().encode("hello")).roles("ADMIN").build();
    //     UserDetails user2= User.builder().username("jai").password(passwordEncoder().encode("kumar")).roles("ADMIN").build();
        
    //     return new InMemoryUserDetailsManager(user1,user2);
        
    // }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


    //auth bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration buiilder) throws Exception{
        return buiilder.getAuthenticationManager();
    }
}
