package com.grupo18.nocountry.greenpoint.redeemreward;


import com.grupo18.nocountry.greenpoint.exceptions.UserNotFoundException;
import com.grupo18.nocountry.greenpoint.user.User;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardTransactionServiceImpl implements RewardTransactionService {

    private final RewardTransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public void save(RewardTransaction rewardTransaction) {
        transactionRepository.save(rewardTransaction);
    }

    @Override
    public Page<RewardTransactionResponse> getAll(Pageable pageable) {
        Page<RewardTransaction> rewardTransactions = transactionRepository.findAll(pageable);
        List<RewardTransactionResponse> transactionResponses = new ArrayList<>();
        for (RewardTransaction rt : rewardTransactions) {
            transactionResponses.add(mapper.map(rt,RewardTransactionResponse.class));
        }
        return new PageImpl<>(transactionResponses,pageable,rewardTransactions.getTotalElements());
    }

    @Override
    public Page<RewardTransactionResponse> getAllByUserId(Pageable pageable, Long id) {
        userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("El usuario con el id "+id+" no existe.")
        );
        Page<RewardTransaction> rewardTransactions = transactionRepository.findAllByUserId(pageable,id);
        List<RewardTransactionResponse> transactionResponses = new ArrayList<>();
        for (RewardTransaction rt : rewardTransactions) {
            transactionResponses.add(mapper.map(rt,RewardTransactionResponse.class));
        }
        return new PageImpl<>(transactionResponses,pageable,rewardTransactions.getTotalElements());
    }
}
