package com.grupo18.nocountry.greenpoint.pointsystem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecycledItemRepository extends JpaRepository<RecycledItem,Long> {

    List<RecycledItem> findAllByRecyclableDetailsId(Long id);
}
