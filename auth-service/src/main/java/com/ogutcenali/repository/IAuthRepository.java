package com.ogutcenali.repository;

import com.ogutcenali.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findByEmail(String email);
    boolean existsByEmail(String email);
}
