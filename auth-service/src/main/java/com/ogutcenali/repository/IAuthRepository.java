package com.ogutcenali.repository;

import com.ogutcenali.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {
}
