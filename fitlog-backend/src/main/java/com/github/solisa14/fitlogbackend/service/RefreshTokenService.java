package com.github.solisa14.fitlogbackend.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.solisa14.fitlogbackend.model.RefreshToken;
import com.github.solisa14.fitlogbackend.model.User;
import com.github.solisa14.fitlogbackend.repository.RefreshTokenRepository;

/**
 * Service class for managing refresh tokens. It handles the creation, retrieval, and deletion of
 * refresh tokens, which are used to maintain user sessions.
 */
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    /**
     * Creates a new refresh token for a given user. The token is generated with a 7-day expiry
     * period.
     *
     * @param user The User entity for whom the token is created.
     * @return The newly created RefreshToken entity.
     */
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken(UUID.randomUUID().toString(), // Generate a
                                                                                   // unique token
                                                                                   // string
                LocalDateTime.now().plusDays(7), // Set expiry to 7 days from now
                user);
        return refreshTokenRepository.save(refreshToken);
    }

    /**
     * Finds a refresh token by its token string.
     * 
     * @param token The token string to search for.
     * @return An Optional containing the RefreshToken if found, otherwise empty.
     */
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    /**
     * Deletes all refresh tokens associated with a specific user. This operation is transactional.
     * 
     * @param user The User entity whose refresh tokens are to be deleted.
     */
    @Transactional
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }

    /**
     * Deletes a refresh token by its token string. This operation is transactional.
     * 
     * @param token The token string of the refresh token to be deleted.
     */
    @Transactional
    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
