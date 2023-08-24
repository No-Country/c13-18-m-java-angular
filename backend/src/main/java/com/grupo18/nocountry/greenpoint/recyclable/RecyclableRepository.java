package com.grupo18.nocountry.greenpoint.recyclable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclableRepository extends JpaRepository<Long, Recyclable> {
    Recyclable findById(Long id);
}
