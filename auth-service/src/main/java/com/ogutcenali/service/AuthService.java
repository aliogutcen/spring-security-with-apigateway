package com.ogutcenali.service;

import com.ogutcenali.dto.request.AuthRequest;
import com.ogutcenali.dto.response.AuthResponse;
import com.ogutcenali.model.Auth;
import com.ogutcenali.repository.IAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final IAuthRepository authRepository;
    private final JwtUtil jwt;

    public void register(AuthRequest request) {

        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        Auth auth = Auth.builder()
                .password(request.getPassword())
                .email(request.getEmail())
                .roles("ADMIN")
                .createdDate(LocalDateTime.now())
                .createdBy("melih")
                .build();
        authRepository.save(auth);

    }

    public AuthResponse login(AuthRequest request) {
        Optional<Auth> auth = authRepository.findByEmail(request.getEmail());
        if (auth.isEmpty()) throw new RuntimeException("Auth does not found");
        String accessToken = jwt.generate(auth.get(), "ACCESS");
        String refreshToken = jwt.generate(auth.get(), "REFRESH");

        return AuthResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();

    }
}
