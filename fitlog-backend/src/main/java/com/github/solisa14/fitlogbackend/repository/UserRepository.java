package com.github.solisa14.fitlogbackend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.solisa14.fitlogbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
