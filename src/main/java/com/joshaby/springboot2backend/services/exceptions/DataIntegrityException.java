package com.joshaby.springboot2backend.services.exceptions;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
