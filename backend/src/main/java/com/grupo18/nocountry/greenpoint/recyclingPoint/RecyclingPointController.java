package com.grupo18.nocountry.greenpoint.recyclingPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/RecyclingPoint")
public class RecyclingPointController {

    private final RecyclingPointService recyclingService;

    @Autowired
    public RecyclingPointController(RecyclingPointService recyclingService) {
        this.recyclingService = recyclingService;
    }

    @PostMapping
    public ResponseEntity<?> createRecyclingPoint(@RequestBody RecyclingPoint recyclingPoint) {
        recyclingService.createRecyclingPoint(recyclingPoint);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchRecyclingPoint(@PathVariable Long id) {
        Optional<RecyclingPoint> recyclingPoint = recyclingService.searchRecyclingPoint(id);
        return recyclingPoint.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyRecyclingPoint(@PathVariable Long id, @RequestBody RecyclingPoint recyclingPoint) {
        recyclingPoint.setId(id);  // Assuming you have a setId method in RecyclingPoint
        recyclingService.modifyRecyclingPoint(recyclingPoint);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecyclingPoint(@PathVariable Long id) {
        recyclingService.deleteRecyclingPoint(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecyclingPoint>> searchAllRecyclingPoints() {
        List<RecyclingPoint> recyclingPoints = recyclingService.searchAll();
        return ResponseEntity.ok(recyclingPoints);
    }
}
