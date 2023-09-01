package com.grupo18.nocountry.greenpoint.redeemreward;

import com.grupo18.nocountry.greenpoint.reward.RewardDTO;
import com.grupo18.nocountry.greenpoint.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardTransactionResponse {

    private UserResponse user;
    private RewardDTO reward;
    private LocalDateTime timestamp;
}
