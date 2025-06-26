package com.github.solisa14.fitlogbackend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Global exception handler for the application. This class uses @RestControllerAdvice to provide
 * centralized exception handling across all @RequestMapping methods.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles MethodArgumentNotValidException, typically thrown when validation on an argument
     * annotated with @Valid fails.
     *
     * @param e       The MethodArgumentNotValidException instance.
     * @param request The HttpServletRequest that resulted in the exception.
     * @return A ResponseEntity containing ErrorResponse with BAD_REQUEST status and error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        // Extracts field errors and formats them into a list of strings
        List<String> errorMessages = errors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessages.toString(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Handles EmailAlreadyExistsException, a custom exception thrown when a user tries to register
     * with an email that already exists in the database.
     *
     * @param e       The EmailAlreadyExistsException instance.
     * @param request The HttpServletRequest that resulted in the exception.
     * @return A ResponseEntity containing ErrorResponse with CONFLICT status and error details.
     */
    @ExceptionHandler(com.github.solisa14.fitlogbackend.exception.EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(
            com.github.solisa14.fitlogbackend.exception.EmailAlreadyExistsException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Handles ResourceNotFoundException, a custom exception thrown when a requested resource is not
     * found in the system.
     *
     * @param e       The ResourceNotFoundException instance.
     * @param request The HttpServletRequest that resulted in the exception.
     * @return A ResponseEntity containing ErrorResponse with NOT_FOUND status and error details.
     */
    @ExceptionHandler(com.github.solisa14.fitlogbackend.exception.ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            com.github.solisa14.fitlogbackend.exception.ResourceNotFoundException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handles UsernameNotFoundException, thrown by Spring Security when a user is not found during
     * the authentication process.
     *
     * @param e       The UsernameNotFoundException instance.
     * @param request The HttpServletRequest that resulted in the exception.
     * @return A ResponseEntity containing ErrorResponse with NOT_FOUND status and error details.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(
            UsernameNotFoundException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handles BadCredentialsException, thrown by Spring Security when authentication fails due to
     * incorrect credentials.
     *
     * @param e       The BadCredentialsException instance.
     * @param request The HttpServletRequest that resulted in the exception.
     * @return A ResponseEntity containing ErrorResponse with UNAUTHORIZED status and error details.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                "Email or password is incorrect",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    /**
     * Handles AccessDeniedException, thrown by Spring Security when an authenticated user attempts
     * to access a resource they are not authorized to access.
     *
     * @param e       The AccessDeniedException instance.
     * @param request The HttpServletRequest that resulted in the exception.
     * @return A ResponseEntity containing ErrorResponse with FORBIDDEN status and error details.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                "You do not have permission to access this resource.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    /**
     * Handles all other unhandled exceptions, serving as a global fallback. It's advisable to log
     * the exception here for debugging purposes.
     *
     * @param e       The Exception instance.
     * @param request The HttpServletRequest that resulted in the exception.
     * @return A ResponseEntity containing ErrorResponse with INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e, HttpServletRequest request) {
        log.error("Unhandled exception occurred: ", e);
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
