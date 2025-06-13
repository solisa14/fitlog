package com.github.solisa14.fitlogbackend.service;

import com.github.solisa14.fitlogbackend.dto.UserRegistrationDto;
import com.github.solisa14.fitlogbackend.dto.UserUpdateDto;
import com.github.solisa14.fitlogbackend.exception.EmailAlreadyExistsException;
import com.github.solisa14.fitlogbackend.exception.ResourceNotFoundException;
import com.github.solisa14.fitlogbackend.model.User;
import com.github.solisa14.fitlogbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for user-related operations such as registration, updates, and deletion.
 * It interacts with the UserRepository and uses PasswordEncoder for security.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user with the provided details.
     * It checks for existing email and encodes the password before saving.
     *
     * @param registrationDto DTO containing user registration data.
     * @return The saved User entity.
     * @throws EmailAlreadyExistsException if the email is already in use.
     */
    public User registerUser(UserRegistrationDto registrationDto) throws EmailAlreadyExistsException {
        // Check if email already exists to prevent duplicates
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already associated with an existing user");
        }
        User newUser = new User(
                registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()) // Securely encode the password
        );
        return userRepository.save(newUser);
    }

    /**
     * Updates an existing user's information.
     * It finds the user by ID, updates email and password (encoded).
     * @param id The ID of the user to update.
     * @param updateDto DTO containing user update data.
     * @return The updated User entity.
     * @throws ResourceNotFoundException if the user with the given ID is not found.
     */
    public User updateUser(Long id, UserUpdateDto updateDto) throws ResourceNotFoundException {
        // Find the user or throw an exception if not found
        Optional<User> possibleUser = userRepository.findById(id);
        if (possibleUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        User updatedUser = possibleUser.get();
        updatedUser.setEmail(updateDto.getEmail());
        updatedUser.setPassword(passwordEncoder.encode(updateDto.getPassword())); // Encode the new password
        return userRepository.save(updatedUser);
    }

    /**
     * Deletes a user by their ID.
     * @param id The ID of the user to be deleted.
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
