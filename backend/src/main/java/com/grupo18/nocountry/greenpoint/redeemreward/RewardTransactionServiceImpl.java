package com.grupo18.nocountry.greenpoint.redeemreward;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardTransactionServiceImpl implements RewardTransactionService {

    private final RewardTransactionRepository transactionRepository;
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
        return new PageImpl<>(transactionResponses,pageable,transactionResponses.size());
    }
}
