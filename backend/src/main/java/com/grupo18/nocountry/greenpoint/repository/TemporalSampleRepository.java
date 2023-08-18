package com.grupo18.nocountry.greenpoint.repository;

import com.grupo18.nocountry.greenpoint.model.TemporalSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporalSampleRepository extends JpaRepository<TemporalSample, Long> {
}
