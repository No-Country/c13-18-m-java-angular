package com.grupo18.nocountry.greenpoint.redeemreward;

import com.grupo18.nocountry.greenpoint.exceptions.InsufficientPointsException;
import com.grupo18.nocountry.greenpoint.exceptions.OutOfStockException;
import com.grupo18.nocountry.greenpoint.exceptions.RewardNotFoundException;
import com.grupo18.nocountry.greenpoint.reward.Reward;
import com.grupo18.nocountry.greenpoint.reward.RewardRepository;
import com.grupo18.nocountry.greenpoint.user.User;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RedeemRewardServiceImpl implements RedeemRewardService{

    private final UserRepository userRepository;
    private final RewardRepository rewardRepository;
    private final RewardTransactionRepository transactionRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void redeem(RedeemRewardRequest request) {

        Reward reward = rewardRepository.findById(request.getRewardId()).orElseThrow(
                ()->new RewardNotFoundException("El premio con el id "+request.getRewardId()+" no existe.")
        );
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                ()-> new UsernameNotFoundException("El usuario con el id "+request.getUserId()+" no existe.")
        );
        int requiredPoints= reward.getPrice();
        Long userPoints = user.getPoints();

        if(userPoints< requiredPoints){
            throw new InsufficientPointsException("Puntos insuficientes");
        }
        user.setPoints(userPoints-requiredPoints);
        userRepository.save(user);
        int actualStock = reward.getInventory().getStock();
        if(actualStock<1){
            throw new OutOfStockException("No hay stock.");
        }
        reward.getInventory().setStock(actualStock-1);

        transactionRepository.save(RewardTransaction.builder()
                        .reward(reward)
                        .user(user)
                        .build()
        );


    }
}
