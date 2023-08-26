package com.ogutcenali.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
}
