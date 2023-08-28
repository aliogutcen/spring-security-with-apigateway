package com.ogutcenali.mapper;

import com.ogutcenali.dto.request.AuthRequest;
import com.ogutcenali.dto.response.AuthResponse;
import com.ogutcenali.model.Auth;
import com.ogutcenali.model.enums.ERole;
import lombok.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;


@Builder
@Controller
public class AuthMapper {


    public Auth toEntity(AuthRequest dto) {
        return Auth.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .createdBy(ERole.ADMIN)
                .createdDate(LocalDateTime.now())
                .roles(ERole.USER)
                .isAccountNonLocked(true)
                .isEnabled(false)
                .isAccountNonExpired(true)
                .build();
    }

}
