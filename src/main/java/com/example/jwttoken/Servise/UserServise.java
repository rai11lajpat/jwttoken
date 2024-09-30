package com.example.jwttoken.Servise;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.jwttoken.Models.User;

@Service
public class UserServise {

    private List<User> stoUsers=new ArrayList<>();

    public UserServise(){
        stoUsers.add(new User(UUID.randomUUID().toString(),"Lajpat","lr@gmal.com"));
    }

    public List<User> getuser(){
        return this.stoUsers;
    }
}
