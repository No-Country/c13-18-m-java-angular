package com.grupo18.nocountry.greenpoint.redeemreward;

import com.grupo18.nocountry.greenpoint.exceptions.InsufficientPointsException;
import com.grupo18.nocountry.greenpoint.exceptions.OutOfStockException;
import com.grupo18.nocountry.greenpoint.exceptions.RewardNotFoundException;
import com.grupo18.nocountry.greenpoint.reward.Reward;
import com.grupo18.nocountry.greenpoint.reward.RewardRepository;
import com.grupo18.nocountry.greenpoint.user.User;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class RedeemRewardServiceImpl implements RedeemRewardService{

    private final UserRepository userRepository;
    private final RewardRepository rewardRepository;
    private final RewardTransactionRepository transactionRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RedeemResponse redeem(RedeemRewardRequest request) {

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
        String code = voucherCode(reward);
        transactionRepository.save(RewardTransaction.builder()
                        .reward(reward)
                        .user(user)
                        .code(code)
                        .build()
        );

        return new RedeemResponse(code);
    }


    public String voucherCode(Reward reward){
        return reward.getName().substring(0,4)
                .replace(" ","-").toUpperCase()
                .concat("-")
                .concat(RandomString.make(5)).toUpperCase()
                .concat("-")
                .concat(LocalDate.now().getMonth().getDisplayName(TextStyle.NARROW, Locale.forLanguageTag("ES")).toUpperCase())
                .concat(String.valueOf(LocalDate.now().getYear()));
    }
}
