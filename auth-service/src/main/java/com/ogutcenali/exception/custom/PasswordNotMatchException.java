package com.ogutcenali.exception.custom;

public class PasswordNotMatchException extends RuntimeException{

    public PasswordNotMatchException(String message){
        super(message);
    }
}
