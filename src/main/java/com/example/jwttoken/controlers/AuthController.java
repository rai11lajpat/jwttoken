package com.example.jwttoken.controlers;

import javax.management.RuntimeErrorException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwttoken.Entities.User;
import com.example.jwttoken.Models.JwtReq;
import com.example.jwttoken.Models.JwtResp;
import com.example.jwttoken.Servise.UserServise;
import com.example.jwttoken.jwtSecurity.JwtHelper;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserServise userServise;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager manager;

    private Logger logger = Logger.getLogger(AuthController.class);


   @PostMapping("/login")
   public ResponseEntity<JwtResp> login(@RequestBody JwtReq request) {
       
        this.doAuthenticate(request.getEmail(),request.getPassword());
       
        UserDetails userDetails=userDetailsService.loadUserByUsername(request.getEmail());
        String token=this.jwtHelper.generateToken(userDetails);
       
        JwtResp response =JwtResp.builder()
        .jwtTokem(token)
        .username(userDetails.getUsername()).build();
       logger.info(response);
        return new ResponseEntity<>(response,HttpStatus.OK);
   }

   @PostMapping("/register")
   public void registerUser(@RequestBody User user) {
        userServise.createUser(user);
       //TODO: process POST request
       
    
   }
   




   private void doAuthenticate(String email,String password){
    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException exception) {
            throw new RuntimeErrorException(null, "Invald user name password!");
            
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String ExceptionHandler(){
        return "Credential invalid..";
    }
    
}
