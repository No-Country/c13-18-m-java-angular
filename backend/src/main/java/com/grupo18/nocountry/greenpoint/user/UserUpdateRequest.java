package com.grupo18.nocountry.greenpoint.user;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserUpdateRequest {
    @Size(min = 3,max = 65)
    private String lastname;
    @Size(min = 3,max = 65)
    private String firstname;
    @Size(min = 3,max = 65)
    private String country;
}
