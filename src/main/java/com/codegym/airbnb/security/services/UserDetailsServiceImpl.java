package com.codegym.airbnb.security.services;

import com.codegym.airbnb.message.request.SignUpForm;
import com.codegym.airbnb.model.User;
import com.codegym.airbnb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User Not Found with -> username or email : " + username))
                        ;

        return UserPrinciple.build(user);
    }
    public User save(SignUpForm user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
}