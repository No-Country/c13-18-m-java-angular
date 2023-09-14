package com.grupo18.nocountry.greenpoint.auth.token;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ConfirmTokenRequest {
    private String token;
}
