package com.grupo18.nocountry.greenpoint.pointsystem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecyclingTransactionRepository extends JpaRepository<RecyclingTransaction,Long> {
    Page<RecyclingTransaction> findAllByUserId(Pageable page, Long id);
}
