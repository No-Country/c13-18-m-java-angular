package com.grupo18.nocountry.greenpoint.recyclable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecyclableService {

    private final RecyclableRepository recyclableRepository;

    @Autowired
    public RecyclableService(RecyclableRepository recyclableRepository) {
        this.recyclableRepository = recyclableRepository;
    }

    public Recyclable getById(Long id) {
        return recyclableRepository.findById(id);
    }
}
