package com.ogutcenali.exception.custom;

public class ActivationCodeExpiredException extends RuntimeException {
    public ActivationCodeExpiredException(String message) {
        super(message);
    }
}
