package com.ogutcenali.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthRequest {


    private String email;

    private String password;
}
