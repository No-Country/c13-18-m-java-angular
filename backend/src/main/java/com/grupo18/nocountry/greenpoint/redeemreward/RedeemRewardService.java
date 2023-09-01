package com.grupo18.nocountry.greenpoint.redeemreward;

import com.grupo18.nocountry.greenpoint.reward.RewardDTO;
import com.grupo18.nocountry.greenpoint.user.User;

public interface RedeemRewardService {
    void redeem(RedeemRewardRequest request);
}
