package com.grupo18.nocountry.greenpoint.controller;

import com.grupo18.nocountry.greenpoint.model.TemporalSample;
import com.grupo18.nocountry.greenpoint.service.TemporalSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/sample")
public class TemporalSampleController {
    private final TemporalSampleService temporalSampleService;

    @Autowired
    public TemporalSampleController(TemporalSampleService temporalSampleService) {
        this.temporalSampleService = temporalSampleService;
    }

    @RequestMapping
    public ResponseEntity<List<TemporalSample>> listarMuestras() {
        return ResponseEntity.ok(temporalSampleService.mostrarLasMuestras());
    }
}
