package com.grupo18.nocountry.greenpoint.auth.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterTokenResponse {
    private String token;
    private String confirmationUrl;
}
