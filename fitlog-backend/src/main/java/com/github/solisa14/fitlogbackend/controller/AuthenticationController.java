package com.github.solisa14.fitlogbackend.controller;

import com.github.solisa14.fitlogbackend.dto.AuthenticationRequestDto;
import com.github.solisa14.fitlogbackend.dto.AuthenticationResponseDto;
import com.github.solisa14.fitlogbackend.dto.UserRegistrationDto;
import com.github.solisa14.fitlogbackend.model.RefreshToken;
import com.github.solisa14.fitlogbackend.model.User;
import com.github.solisa14.fitlogbackend.service.RefreshTokenService;
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

import java.util.Map;

/**
 * Controller for handling user authentication and registration using RESTful
 * API endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtUtil jwtUtil, RefreshTokenService refreshTokenService,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
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
    public ResponseEntity<AuthenticationResponseDto> login(
            @Valid @RequestBody AuthenticationRequestDto authenticationRequest) {
        // Authenticate user credentials using the AuthenticationManager
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));
        // If authentication is successful, retrieve user details and generate JWT token and
        // refresh
        // token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        RefreshToken refreshToken
                = refreshTokenService.createRefreshToken((User) userDetails);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new AuthenticationResponseDto(userDetails.getUsername(),
                        token, jwtUtil.getExpirationTime(),
                        refreshToken.getToken()));
    }

    /**
     * Handles user registration by creating a new user account.
     *
     * @param userRegistrationDto the user registration data
     * @return ResponseEntity containing the authentication response with JWT
     * token and refresh token
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(
            @Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        User user = userService.registerUser(userRegistrationDto);

        // Authenticate the newly registered user to generate JWT token and refresh token
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userRegistrationDto.getEmail(),
                        userRegistrationDto.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthenticationResponseDto(userDetails.getUsername(),
                        token, jwtUtil.getExpirationTime(),
                        refreshToken.getToken()));
    }

    /**
     * Handles user logout by deleting the refresh token associated with the
     * user.
     *
     * @param request a map containing the refresh token
     * @return ResponseEntity indicating a successful logout
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken != null) {
            refreshTokenService.deleteByToken(refreshToken);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Refreshes the access token using a valid refresh token.
     *
     * @param request a map containing the refresh token
     * @return ResponseEntity containing the new authentication response with
     * access token and refresh token
     */
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponseDto> refresh(
            @RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        // Look up the refresh token and check if it is valid
        return refreshTokenService.findByToken(refreshToken)
                .filter(token -> !token.isExpired()).map(token -> {
                    String newAccessToken
                            = jwtUtil.generateToken(token.getUser());
                    return ResponseEntity.ok(new AuthenticationResponseDto(
                            token.getUser().getUsername(),
                            newAccessToken, jwtUtil.getExpirationTime(),
                            refreshToken));
                }).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
