package com.grupo18.nocountry.greenpoint.redeemreward;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RedeemRewardRequest {

    private Long userId;
    private Long rewardId;

}
