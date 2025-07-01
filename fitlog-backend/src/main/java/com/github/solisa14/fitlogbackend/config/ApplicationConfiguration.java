package com.github.solisa14.fitlogbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.github.solisa14.fitlogbackend.repository.UserRepository;

/**
 * Configures the application beans, including user details service,
 * authentication manager, authentication provider, and password encoder. This
 * configuration is essential for setting up Spring Security throughout the
 * application.
 */
@Configuration
public class ApplicationConfiguration {

    private final UserRepository userRepository;

    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Provides a UserDetailsService bean that retrieves user details by email.
     * This is used for authentication.
     *
     * @return UserDetailsService implementation
     */
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + username + " not found"));
    }

    /**
     * Provides an AuthenticationManager bean for managing authentication.
     *
     * @param configuration the AuthenticationConfiguration
     * @return the AuthenticationManager
     * @throws Exception if an error occurs while creating the
     * AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Provides an AuthenticationProvider bean that uses the UserDetailsService
     * and PasswordEncoder. This is used for authenticating users.
     *
     * @return AuthenticationProvider implementation
     */
    @Bean
    @SuppressWarnings("unused")
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider
                = new DaoAuthenticationProvider(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Provides a PasswordEncoder bean that uses BCrypt for encoding passwords.
     * This is used to securely store and verify user passwords.
     *
     * @return PasswordEncoder implementation
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
