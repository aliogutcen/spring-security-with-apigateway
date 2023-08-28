package com.ogutcenali.model;

import com.ogutcenali.model.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "auth-serive")
public class Auth extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private ERole roles;

    private boolean isAccountNonLocked;

    private boolean isAccountNonExpired;

    private boolean isEnabled;


}
