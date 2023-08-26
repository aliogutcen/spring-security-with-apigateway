package com.ogutcenali.controller;

import com.ogutcenali.dto.request.AuthRequest;
import com.ogutcenali.dto.response.AuthResponse;
import com.ogutcenali.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/ali")
    public String deneme() {
        return "deneme";
    }
}
