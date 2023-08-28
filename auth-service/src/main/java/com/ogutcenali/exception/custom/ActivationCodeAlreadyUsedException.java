package com.ogutcenali.exception.custom;

public class ActivationCodeAlreadyUsedException extends RuntimeException {

    public ActivationCodeAlreadyUsedException(String message) {
        super(message);
    }
}
