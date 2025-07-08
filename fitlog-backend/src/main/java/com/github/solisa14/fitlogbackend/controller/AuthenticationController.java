package com.github.solisa14.fitlogbackend.controller;

import com.github.solisa14.fitlogbackend.dto.AuthenticationRequest;
import com.github.solisa14.fitlogbackend.dto.AuthenticationResponse;
import com.github.solisa14.fitlogbackend.model.User;
import com.github.solisa14.fitlogbackend.service.UserService;
import com.github.solisa14.fitlogbackend.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling user authentication and registration using RESTful
 * API endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtUtil jwtUtil,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    /**
     * Handles user login by authenticating the user's credentials (email and
     * password).
     *
     * @param authenticationRequest the authentication request containing email
     *                              and password
     * @return ResponseEntity containing the authentication response with JWT
     * token and refresh token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticationRequest authenticationRequest) {
        // Authenticate user credentials using the AuthenticationManager
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));
        // If authentication is successful, retrieve user details and generate JWT token an refresh token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new AuthenticationResponse(userDetails.getUsername(),
                        token, jwtUtil.getExpirationTime()));
    }

    /**
     * Handles user registration by creating a new user account.
     *
     * @param authenticationRequest the user registration data
     * @return ResponseEntity containing the authentication response with JWT
     * token and refresh token
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody AuthenticationRequest authenticationRequest) {
        User user = userService.registerUser(authenticationRequest);
        String token = jwtUtil.generateToken(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthenticationResponse(user.getUsername(),
                        token, jwtUtil.getExpirationTime()));
    }
}
