package com.github.solisa14.fitlogbackend.repository;

import com.github.solisa14.fitlogbackend.model.RefreshToken;
import com.github.solisa14.fitlogbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);

    void deleteByToken(String token);
}
