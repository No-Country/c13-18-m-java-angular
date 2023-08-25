package com.grupo18.nocountry.greenpoint.pointsystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RedeemRequest {

    private Long userId;
    private String code;
}
