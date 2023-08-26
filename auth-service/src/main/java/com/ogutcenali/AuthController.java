package com.ogutcenali;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/deneme")
    public String auth() {
        return "Adam mısın MELİH";
    }

    @GetMapping("/deneme1")
    public String auth1() {
        return "Adam mısın MELİH";
    }
}
