package com.grupo18.nocountry.greenpoint.pointsystem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface RecyclableDetailsRepository extends JpaRepository<RecyclableDetails,Long> {
    Optional<RecyclableDetails> findByCodeAndRedeemedFalse(String code);

}
