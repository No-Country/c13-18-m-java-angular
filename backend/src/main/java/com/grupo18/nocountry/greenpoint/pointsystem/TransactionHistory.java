package com.grupo18.nocountry.greenpoint.pointsystem;

import com.grupo18.nocountry.greenpoint.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionHistory {
    private UserResponse user;
    private int pointsEarned;
    private int recycledItems;
    private LocalDateTime timestamp;

}
