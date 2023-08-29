package com.ogutcenali.service;

import com.ogutcenali.dto.request.ActivationCodeRequest;
import com.ogutcenali.exception.custom.ActivationCodeAlreadyUsedException;
import com.ogutcenali.exception.custom.ActivationCodeExpiredException;
import com.ogutcenali.exception.custom.ActivationCodeNotFoundException;
import com.ogutcenali.model.ActivationCode;
import com.ogutcenali.model.enums.ERole;
import com.ogutcenali.repository.IActivationCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActivationCodeService {

    private final IActivationCodeRepository activationCodeRepository;
    private final AuthService authService;
    private final MailService mailService;

    public ActivationCodeService(IActivationCodeRepository activationCodeRepository, @Lazy AuthService authService, MailService mailService) {
        this.activationCodeRepository = activationCodeRepository;
        this.authService = authService;
        this.mailService = mailService;
    }

    @Transactional
    public void generateActivationCode(String email) {
        String code = generateCode();
        ActivationCode activationCode = ActivationCode.builder()
                .code(code)
                .expirationCode(calculateExpirationTime())
                .isUsed(false)
                .email(email)
                .createdDate(LocalDateTime.now())
                .createdBy(ERole.ADMIN)
                .build();
        activationCodeRepository.save(activationCode);
        mailService.activationCodeSendEmail(email, code);
    }

    @Transactional
    public void processActivationCodeForAccount(ActivationCodeRequest request) {
        ActivationCode activationCode = retrieveActivationCodeByEmail(request.getEmail());
        checkExpiredCodeTime(activationCode);
        checkActivationCodeMatching(activationCode, request.getCode());
        isUseCodeBefore(activationCode);

        activationCode.setUsed(true);
        activationCode.setLastModifiedDate(LocalDateTime.now());
        activationCodeRepository.save(activationCode);

        //activation code success and send auth service enabled true
        authService.successActivationAfterAuthEnabled(activationCode.getEmail());

    }

    protected void isUseCodeBefore(ActivationCode activationCode) {
        if (activationCode.isUsed())
            throw new ActivationCodeAlreadyUsedException("Activation code already used!!!");
    }

    protected String generateCode() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    protected LocalDateTime calculateExpirationTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.plusHours(24);
    }

    protected ActivationCode retrieveActivationCodeByEmail(String email) {
        Optional<ActivationCode> activationCode = activationCodeRepository.findOptionalByEmail(email);
        if (activationCode.isEmpty())
            throw new ActivationCodeNotFoundException("No activation code found matching" + "the email address" + email);
        return activationCode.get();
    }

    protected void checkActivationCodeMatching(ActivationCode activationCode, String providedCode) {
        if (!activationCode.getCode().equals(providedCode)) {
            throw new ActivationCodeNotFoundException("The provided activation code does not match.");
        }
    }

    protected void checkExpiredCodeTime(ActivationCode activationCode) {
        if (activationCode.getExpirationCode().isBefore(LocalDateTime.now())) {
            throw new ActivationCodeExpiredException("Activation code has expired.");
        }
    }
}

