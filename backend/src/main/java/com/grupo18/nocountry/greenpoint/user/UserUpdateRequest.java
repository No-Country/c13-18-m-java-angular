package com.grupo18.nocountry.greenpoint.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserUpdateRequest {
    private String lastname;
    private String firstname;
    private String country;
}
