package com.grupo18.nocountry.greenpoint.redeemreward;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RedeemRewardRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long rewardId;

}
