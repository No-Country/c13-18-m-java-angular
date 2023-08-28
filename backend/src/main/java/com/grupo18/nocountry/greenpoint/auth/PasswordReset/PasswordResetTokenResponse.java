package com.grupo18.nocountry.greenpoint.auth.PasswordReset;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PasswordResetTokenResponse {
        private UUID token;
}
