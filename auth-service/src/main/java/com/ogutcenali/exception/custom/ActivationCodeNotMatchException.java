package com.ogutcenali.exception.custom;

public class ActivationCodeNotMatchException extends RuntimeException {

    public ActivationCodeNotMatchException(String message){
        super(message);
    }
}
