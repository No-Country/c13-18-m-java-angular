package com.grupo18.nocountry.greenpoint.redeemreward;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RewardTransactionService {
    void save(RewardTransaction rewardTransaction);

    Page<RewardTransactionResponse> getAll(Pageable pageable);
    Page<RewardTransactionResponse> getAllByUserId(Pageable pageable,Long id);
}
