package com.github.solisa14.fitlogbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Global exception handler for the application.
 * This class uses @RestControllerAdvice to provide centralized exception handling across all @RequestMapping methods.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles MethodArgumentNotValidException, typically thrown when validation on an argument
     * annotated with @Valid fails.
     *
     * @param e The MethodArgumentNotValidException instance.
     * @return A ResponseEntity containing ProblemDetail with BAD_REQUEST status and error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        // Extracts field errors and formats them into a list of strings
        List<String> errorMessages = errors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST.value());
        problemDetail.setTitle("Bad Request");
        problemDetail.setDetail(errorMessages.toString()); // Provides detailed validation error messages
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    /**
     * Handles EmailAlreadyExistsException, a custom exception thrown when a user tries to register
     * with an email that already exists in the database.
     *
     * @param e The EmailAlreadyExistsException instance.
     * @return A ResponseEntity containing ProblemDetail with CONFLICT status and error details.
     */
    @ExceptionHandler(com.github.solisa14.fitlogbackend.exception.EmailAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleEmailAlreadyExistsException(com.github.solisa14.fitlogbackend.exception.EmailAlreadyExistsException e) { // Changed parameter type
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT.value());
        problemDetail.setTitle("Email Already Exists");
        problemDetail.setDetail(e.getMessage()); // Uses the message from the custom exception
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }

    /**
     * Handles ResourceNotFoundException, a custom exception thrown when a requested resource
     * is not found in the system.
     *
     * @param e The ResourceNotFoundException instance.
     * @return A ResponseEntity containing ProblemDetail with NOT_FOUND status and error details.
     */
    @ExceptionHandler(com.github.solisa14.fitlogbackend.exception.ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(com.github.solisa14.fitlogbackend.exception.ResourceNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND.value());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setDetail(e.getMessage()); // Uses the message from the custom exception
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    /**
     * Handles UsernameNotFoundException, thrown by Spring Security when a user is not found
     * during the authentication process.
     *
     * @param e The UsernameNotFoundException instance.
     * @return A ResponseEntity containing ProblemDetail with NOT_FOUND status and error details.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUsernameNotFoundException(UsernameNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND.value());
        problemDetail.setTitle("User Not Found");
        problemDetail.setDetail(e.getMessage()); // Uses the message from the Spring Security exception
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    /**
     * Handles BadCredentialsException, thrown by Spring Security when authentication fails
     * due to incorrect credentials.
     *
     * @param e The BadCredentialsException instance.
     * @return A ResponseEntity containing ProblemDetail with UNAUTHORIZED status and error details.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ProblemDetail> handleBadCredentialsException(BadCredentialsException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED.value());
        problemDetail.setTitle("Invalid Credentials");
        problemDetail.setDetail("Email or password is incorrect"); // Provides a generic message for security reasons
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }

    /**
     * Handles AccessDeniedException, thrown by Spring Security when an authenticated user
     * attempts to access a resource they are not authorized to access.
     *
     * @param e The AccessDeniedException instance.
     * @return A ResponseEntity containing ProblemDetail with FORBIDDEN status and error details.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ProblemDetail> handleAccessDeniedException(AccessDeniedException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN.value());
        problemDetail.setTitle("Forbidden");
        problemDetail.setDetail("You do not have permission to access this resource."); // Provides a clear message
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problemDetail);
    }

    /**
     * Handles all other unhandled exceptions, serving as a global fallback.
     * It's advisable to log the exception here for debugging purposes.
     *
     * @param e The Exception instance.
     * @return A ResponseEntity containing ProblemDetail with INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGlobalException(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        problemDetail.setTitle("Internal Server Error");
        // Provide a generic message to the client for security and user experience
        problemDetail.setDetail("An unexpected error occurred. Please try again later.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
    }
}