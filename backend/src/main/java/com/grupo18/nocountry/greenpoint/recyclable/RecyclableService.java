package com.grupo18.nocountry.greenpoint.recyclable;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecyclableService {

    private final RecyclableRepository recyclableRepository;

    public Recyclable getById(Long id) {
        return recyclableRepository.findById(id).get();
    }
}
