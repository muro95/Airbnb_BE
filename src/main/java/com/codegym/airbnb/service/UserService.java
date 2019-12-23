package com.codegym.airbnb.service;

import com.codegym.airbnb.message.response.UserInformation;
import com.codegym.airbnb.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {
    List<User> findAll();

//    Optional<User> findByUsername(String username);

    User findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    User findById(Long id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    UserInformation findByIdCurrent(Long id);

}