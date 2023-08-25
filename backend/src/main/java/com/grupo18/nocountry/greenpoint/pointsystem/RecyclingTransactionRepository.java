package com.grupo18.nocountry.greenpoint.pointsystem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecyclingTransactionRepository extends JpaRepository<RecyclingTransaction,Long> {
    List<RecyclingTransaction> findAllByUserId(Long id);
}
