package com.codegym.airbnb.service;

import com.codegym.airbnb.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();


    User findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    User findById(Long id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);
}