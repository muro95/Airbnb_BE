package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUsername(String username);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
