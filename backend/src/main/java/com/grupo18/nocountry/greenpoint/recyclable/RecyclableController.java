package com.grupo18.nocountry.greenpoint.recyclable;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recyclable")
@RequiredArgsConstructor
public class RecyclableController {

    private final RecyclableService recyclableService;

    @GetMapping("/{id}")
    public ResponseEntity<Recyclable> getRecyclableById(@PathVariable Long id) {
        return ResponseEntity.ok(recyclableService.getById(id));
    }
}
