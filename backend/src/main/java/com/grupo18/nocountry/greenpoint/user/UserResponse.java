package com.grupo18.nocountry.greenpoint.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String lastname;
    private String firstname;
    private String country;
    private Boolean isEnabled;
    private Long points;

}
