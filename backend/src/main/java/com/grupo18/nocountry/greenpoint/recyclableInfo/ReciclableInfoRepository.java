package com.grupo18.nocountry.greenpoint.recyclableInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReciclableInfoRepository extends JpaRepository<RecyclableInfo, Long> {
    List<RecyclableInfo> findByTag(String tag);
}
