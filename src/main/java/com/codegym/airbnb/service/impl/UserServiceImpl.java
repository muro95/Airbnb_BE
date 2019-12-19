package com.codegym.airbnb.service.impl;

import com.codegym.airbnb.message.response.UserInformation;
import com.codegym.airbnb.model.User;
import com.codegym.airbnb.repository.UserDao;
import com.codegym.airbnb.repository.UserRepository;
import com.codegym.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


//    @Override
//    public Optional<User> findByUsername(String username) {
//
//        return userRepository.findByUsername(username);
//    }
    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserInformation findByIdCurrent(Long id) {
        return userDao.userInformation(id);
    }
}
