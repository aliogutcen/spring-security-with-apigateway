package com.ogutcenali.exception;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Getter
@Controller
public class ExceptionResponse {


    private String message;
    private LocalDateTime dateTime;
    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
