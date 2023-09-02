package com.grupo18.nocountry.greenpoint.recyclableInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReciclableInfoRepository extends JpaRepository<RecyclableInfo, Long> {
    List<RecyclableInfo> findByTag(String tag);
}
