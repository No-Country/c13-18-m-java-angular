package com.grupo18.nocountry.greenpoint.recyclable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecyclableRepository extends JpaRepository<Recyclable, Long> {
    Optional<Recyclable> findByRecyclableType(RecyclableType recyclableType);
}
