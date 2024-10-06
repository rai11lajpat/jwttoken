package com.example.jwttoken.Servise;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwttoken.Entities.User;
import com.example.jwttoken.Repository.UserRepo;

@Service
public class CustomUserServise implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
        User user=userRepo.findByEmail(username).orElseThrow(()->new RuntimeException("user not found"));

        return user;
    }
    
}
