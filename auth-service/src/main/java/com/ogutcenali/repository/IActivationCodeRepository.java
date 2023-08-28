package com.ogutcenali.repository;


import com.ogutcenali.model.ActivationCode;
import com.ogutcenali.service.ActivationCodeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IActivationCodeRepository extends JpaRepository<ActivationCode, UUID> {

    Optional<ActivationCode> findOptionalByEmail(String email);
}
