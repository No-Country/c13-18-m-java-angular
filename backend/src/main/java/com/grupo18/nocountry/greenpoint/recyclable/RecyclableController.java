package com.grupo18.nocountry.greenpoint.recyclable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recyclable")
public class RecyclableController {

    private final RecyclableService recyclableService;

    @Autowired
    public RecyclableController(RecyclableService recyclableService) {
        this.recyclableService = recyclableService;
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Recyclable> getRecyclableById(@PathVariable Long id) {
        return ResponseEntity.ok(recyclableService.getById(id));
    }
}
