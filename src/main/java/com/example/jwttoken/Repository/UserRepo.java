package com.example.jwttoken.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.jwttoken.Entities.User;
@Repository
public interface UserRepo  extends CrudRepository<User,Integer>{
public Optional<User> findByEmail(String email);
    
}  
