package com.ajinkyabhutkar.irctc.repo;

import com.ajinkyabhutkar.irctc.dto.UserDto;
import com.ajinkyabhutkar.irctc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String emailAddress);
    boolean existsByEmail(String emailAddress);
}
