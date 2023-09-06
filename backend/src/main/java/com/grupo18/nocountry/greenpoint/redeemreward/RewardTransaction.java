package com.grupo18.nocountry.greenpoint.redeemreward;


import com.grupo18.nocountry.greenpoint.reward.Reward;
import com.grupo18.nocountry.greenpoint.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Reward reward;
    private String code;
    @CreationTimestamp
    private LocalDateTime timestamp;

}
