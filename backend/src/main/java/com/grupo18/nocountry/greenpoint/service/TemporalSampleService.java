package com.grupo18.nocountry.greenpoint.service;

import com.grupo18.nocountry.greenpoint.model.TemporalSample;
import com.grupo18.nocountry.greenpoint.repository.TemporalSampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemporalSampleService {
    private final TemporalSampleRepository temporalSampleRepository;

    @Autowired
    public TemporalSampleService(TemporalSampleRepository temporalSampleRepository) {
        this.temporalSampleRepository = temporalSampleRepository;
    }

    public List<TemporalSample> mostrarLasMuestras() {
        return temporalSampleRepository.findAll();
    }
}
