package com.github.solisa14.fitlogbackend.service;

import com.github.solisa14.fitlogbackend.exception.EmailAlreadyExistsException;
import com.github.solisa14.fitlogbackend.mapper.UserMapper;
import com.github.solisa14.fitlogbackend.model.User;
import com.github.solisa14.fitlogbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for user-related operations such as registration, updates, and
 * deletion. It interacts with the UserRepository and uses PasswordEncoder for
 * security.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user with the provided details. It checks for existing
     * email and encodes the password before saving.
     *
     * @return The saved User entity.
     * @throws EmailAlreadyExistsException if the email is already in use.
     */
    public User registerUser(User user)
            throws EmailAlreadyExistsException {
        // Check if email already exists to prevent duplicates
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(
                    "Email is already associated with an existing user");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to be deleted.
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
