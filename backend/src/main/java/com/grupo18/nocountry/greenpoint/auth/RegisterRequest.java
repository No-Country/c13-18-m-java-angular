package com.grupo18.nocountry.greenpoint.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @Email
    @NotBlank
    @Size(min = 1,max = 65)
    String username;
    @Size(min = 8,max = 65)
    String password;
    @NotBlank
    @Size(min = 1,max = 65)
    String firstname;

}
