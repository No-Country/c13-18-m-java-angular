package com.grupo18.nocountry.greenpoint.redeemreward;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardTransactionRepository extends JpaRepository<RewardTransaction,Long> {
    Page<RewardTransaction> findAllByUserId(Pageable pageable, Long id);
}
