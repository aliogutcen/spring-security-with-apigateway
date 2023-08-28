package com.ogutcenali.exception;

import com.ogutcenali.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    private final ExceptionResponse exceptionResponse;

    public GlobalExceptionHandler(ExceptionResponse exceptionResponse) {
        this.exceptionResponse = exceptionResponse;
    }


    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyException(UserAlreadyExistsException exception,
                                                             WebRequest webRequest) {
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserAlreadyException(UserNotFoundException exception,
                                                             WebRequest webRequest) {
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ActivationCodeNotFoundException.class})
    public ResponseEntity<Object> handleActivationCodeNotFoundException(ActivationCodeNotFoundException exception,
                                                                        WebRequest request) {
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ActivationCodeExpiredException.class})
    public ResponseEntity<Object> handleActivationCodeNotFoundException(ActivationCodeExpiredException exception,
                                                                        WebRequest request) {
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler({ActivationCodeNotMatchException.class})
    public ResponseEntity<Object> handleActivationCodeNotFoundException(ActivationCodeNotMatchException exception,
                                                                        WebRequest request) {
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler({ActivationCodeAlreadyUsedException.class})
    public ResponseEntity<Object> handleActivationCodeNotFoundException(ActivationCodeAlreadyUsedException exception,
                                                                        WebRequest request) {
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}
