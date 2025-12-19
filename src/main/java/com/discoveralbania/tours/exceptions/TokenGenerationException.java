package com.discoveralbania.tours.exceptions;

public class TokenGenerationException extends Exception {
    public TokenGenerationException() {
        super("Error generating token.");
    }

    public TokenGenerationException(String message) {
        super(message);
    }
}