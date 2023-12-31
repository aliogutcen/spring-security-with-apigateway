package com.ogutcenali.service;

import com.ogutcenali.dto.request.AuthRequest;
import com.ogutcenali.dto.response.AuthResponse;
import com.ogutcenali.exception.custom.PasswordNotMatchException;
import com.ogutcenali.exception.custom.UserAlreadyExistsException;
import com.ogutcenali.exception.custom.UserNotEnabledException;
import com.ogutcenali.exception.custom.UserNotFoundException;
import com.ogutcenali.mapper.AuthMapper;
import com.ogutcenali.model.Auth;
import com.ogutcenali.model.enums.ERole;
import com.ogutcenali.repository.IAuthRepository;
import com.ogutcenali.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {


    private final IAuthRepository authRepository;
    private final JwtUtil jwt;
    private final ActivationCodeService activationCodeService;


    public AuthService(IAuthRepository authRepository, JwtUtil jwt, ActivationCodeService activationCodeService) {
        this.authRepository = authRepository;
        this.jwt = jwt;

        this.activationCodeService = activationCodeService;
    }

    public void register(AuthRequest request) {
        if (authRepository.existsByEmail(request.getEmail()))
            throw new UserAlreadyExistsException("User already exists with the given email");
        Auth auth = AuthMapper.builder().build().toEntity(request);
        auth.setPassword(generateBCryptPassword(request.getPassword()));
        authRepository.save(auth);

        //after save send to activation code for user
        activationCodeService.generateActivationCode(auth.getEmail());


    }

    public AuthResponse login(AuthRequest request) {

        Auth auth = getAuthWithEmail(request.getEmail());
        if (!auth.isEnabled())
            throw new UserNotEnabledException("User is not enabled for login please activate with activate code");
        if (!BCrypt.checkpw(request.getPassword(), auth.getPassword()))
            throw new PasswordNotMatchException("Try your password again");

        String accessToken = jwt.generate(auth, "ACCESS");
        String refreshToken = jwt.generate(auth, "REFRESH");
        return AuthResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }


    public String generateBCryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void successActivationAfterAuthEnabled(String email) {
        Auth auth = getAuthWithEmail(email);
        auth.setEnabled(true);
        auth.setLastModifiedDate(LocalDateTime.now());
        auth.setLastModifiedBy(ERole.ADMIN);
        authRepository.save(auth);
    }

    protected Auth getAuthWithEmail(String email) {
        Optional<Auth> auth = authRepository.findByEmail(email);
        if (auth.isEmpty()) {
            throw new UserNotFoundException("User with email not found: " + email);
        }
        return auth.get();
    }
}
