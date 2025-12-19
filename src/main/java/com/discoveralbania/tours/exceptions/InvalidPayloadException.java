package com.discoveralbania.tours.exceptions;

public class InvalidPayloadException extends Exception {

    public InvalidPayloadException() {
        super("Invalid request body");
    }

    public InvalidPayloadException(String message) {
        super(message);
    }
}