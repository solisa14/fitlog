package com.github.solisa14.fitlogbackend.repository;

import com.github.solisa14.fitlogbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
