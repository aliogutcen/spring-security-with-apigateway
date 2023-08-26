package com.ogutcenali.service;

import com.ogutcenali.dto.request.AuthRequest;
import com.ogutcenali.dto.response.AuthResponse;
import com.ogutcenali.model.Auth;
import com.ogutcenali.repository.IAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final IAuthRepository authRepository;
    private final JwtUtil jwt;

    public AuthResponse register(AuthRequest request) {

        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        Auth auth = Auth.builder()
                .password(request.getPassword())
                .email(request.getEmail())
                .roles("ADMIN")
                .build();

        authRepository.save(auth);

        String accessToken = jwt.generate(auth, "ACCESS");
        String refreshToken = jwt.generate(auth, "REFRESH");

        return AuthResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }
}
