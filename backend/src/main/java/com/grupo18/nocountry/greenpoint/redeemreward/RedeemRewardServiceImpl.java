package com.grupo18.nocountry.greenpoint.redeemreward;

import com.grupo18.nocountry.greenpoint.inventory.InventoryRepository;
import com.grupo18.nocountry.greenpoint.reward.Reward;
import com.grupo18.nocountry.greenpoint.reward.RewardRepository;
import com.grupo18.nocountry.greenpoint.user.User;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RedeemRewardServiceImpl implements RedeemRewardService{

    private final UserRepository userRepository;
    private final RewardRepository rewardRepository;
    private final RewardTransactionRepository transactionRepository;

    @Override
    @Transactional
    public void redeem(RedeemRewardRequest request) {

        Reward reward = rewardRepository.findById(request.getRewardId()).orElseThrow(
                ()->new RuntimeException("El premio con el id "+request.getRewardId()+" no existe.")
        );
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                ()-> new RuntimeException("El usuario con el id "+request.getUserId()+" no existe.")
        );

        if(user.getPoints()< reward.getPrice()){
            throw new RuntimeException("Puntos insuficientes");
        }
        user.setPoints(user.getPoints()- reward.getPrice());
        userRepository.save(user);
        int actualStock = reward.getInventory().getStock();
        reward.getInventory().setStock(actualStock-1);

        transactionRepository.save(RewardTransaction.builder()
                        .reward(reward)
                        .user(user)
                        .build()
        );


    }
}
