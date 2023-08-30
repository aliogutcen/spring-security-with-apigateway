package com.ogutcenali.exception.custom;

public class UserNotEnabledException extends RuntimeException {


    public UserNotEnabledException(String message) {
        super(message);
    }
}
