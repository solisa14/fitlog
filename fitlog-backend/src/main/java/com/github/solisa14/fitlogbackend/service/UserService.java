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

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationDto registrationDto) throws EmailAlreadyExistsException {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already associated with an existing user");
        }
        User newUser = new User(
                registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword())
        );
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, UserUpdateDto updateDto) throws ResourceNotFoundException {
        Optional<User> possibleUser = userRepository.findById(id);
        if (possibleUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        User updatedUser = possibleUser.get();
        updatedUser.setEmail(updateDto.getEmail());
        updatedUser.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        return userRepository.save(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
