package com.grupo18.nocountry.greenpoint.pointsystem;

import com.grupo18.nocountry.greenpoint.recyclable.Recyclable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PointSystemService {
    RecycleResponse recycle(RecycleRequest request) throws Exception;
    void redeem(RedeemRequest request) throws Exception;
    Page<TransactionHistory> getUserTransactionHistory(Pageable pageable, Long id);
    Page<TransactionHistory> getAllTransactions(Pageable pageable);


}
