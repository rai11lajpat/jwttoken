package com.example.jwttoken.Servise;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwttoken.Entities.User;
import com.example.jwttoken.Repository.UserRepo;

@Service
public class UserServise {

    @Autowired
    private UserRepo userRepo;

    @Autowired PasswordEncoder passwordEncoder;

    // private List<User> stoUsers=new ArrayList<>();

    // public UserServise(){
    //     stoUsers.add(new User(UUID.randomUUID().toString(),"Lajpat","lr@gmal.com"));
    // }

    // public List<User> getuser(){
    //     return this.stoUsers;
    // }


    public List<User> getuser(){

        List<User> list=(List<User>)this.userRepo.findAll();
        return list;
    }

    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

    }
}
