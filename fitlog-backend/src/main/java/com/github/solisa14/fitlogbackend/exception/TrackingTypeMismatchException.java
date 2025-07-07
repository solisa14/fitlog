package com.github.solisa14.fitlogbackend.exception;

public class TrackingTypeMismatchException extends RuntimeException {
    public TrackingTypeMismatchException() {
        super("Tracking type does not match the exercise's tracking type");
    }

    public TrackingTypeMismatchException(String message) {
        super(message);
    }
}