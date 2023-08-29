package com.grupo18.nocountry.greenpoint.pointsystem;

import com.grupo18.nocountry.greenpoint.recyclable.Recyclable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PointSystemService {
    RecycleResponse recycle(RecycleRequest request) ;
    void redeem(RedeemRequest request);
    Page<TransactionHistory> getUserTransactionHistory(Pageable pageable, Long id);
    Page<TransactionHistory> getAllTransactions(Pageable pageable);


}
