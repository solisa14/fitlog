package com.github.solisa14.fitlogbackend.exception;

/**
 * Custom exception to indicate that an attempt was made to create a user with an email
 * that already exists in the system.
 * This exception typically results in an HTTP 409 Conflict response.
 */
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email is already associated with an existing user");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }


}
