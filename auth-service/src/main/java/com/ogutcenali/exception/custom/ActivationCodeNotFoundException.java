package com.ogutcenali.exception.custom;

public class ActivationCodeNotFoundException extends RuntimeException {
    public ActivationCodeNotFoundException(String message) {
        super(message);
    }
}
