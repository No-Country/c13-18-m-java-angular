package com.grupo18.nocountry.greenpoint.recyclingPoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface RecyclingPointRepository extends JpaRepository<RecyclingPoint, Long>{

    @Query("SELECT rp FROM RecyclingPoint rp WHERE rp.openingTime >= :openingTime AND rp.closingTime <= :closingTime")
    List<RecyclingPoint> findByOpeningAndClosingTime(@Param("openingTime") LocalTime openingTime, @Param("closingTime") LocalTime closingTime);

    @Query("SELECT rp FROM RecyclingPoint rp WHERE rp.comuna = :comuna")
    List<RecyclingPoint> findByComuna(@Param("comuna") int comuna);

}
