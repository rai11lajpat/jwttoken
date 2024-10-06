package com.example.jwttoken.controlers;

import org.springframework.web.bind.annotation.RestController;

import com.example.jwttoken.Entities.User;
import com.example.jwttoken.Servise.UserServise;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
public class Home {

    @Autowired
    UserServise userServise;

        @GetMapping("/home/user")
        public Iterable<User> getMethodName() {
            System.out.println("home fireddddd....");
            return userServise.getuser();
        }



        @GetMapping("/home/current-user")
        public String getUserName(Principal principal) {
            return principal.getName();
        }
        
        
}
