package com.grupo18.nocountry.greenpoint.recyclingPoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@ApiResponses(value = {

        @ApiResponse(responseCode = "200", description = "Ok",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = RecyclingPoint.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found",
                content = @Content) ,
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
                content = @Content )})

@RestController
@RequestMapping("/api/v1/RecyclingPoint")
public class RecyclingPointController {

    private final RecyclingPointService recyclingService;

    @Autowired
    public RecyclingPointController(RecyclingPointService recyclingService) {
        this.recyclingService = recyclingService;
    }

    @Operation(summary = "Create a RecyclingPoint")
    @PostMapping
    public ResponseEntity<?> createRecyclingPoint(@RequestBody RecyclingPoint recyclingPoint) {
        recyclingService.createRecyclingPoint(recyclingPoint);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Get a RecyclingPoint by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> searchRecyclingPoint(@Parameter(description = "ID of RecyclingPoint to be searched")@PathVariable long id) {
        Optional<RecyclingPoint> recyclingPoint = recyclingService.searchRecyclingPoint(id);
        return recyclingPoint.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modify a RecyclingPoint")
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyRecyclingPoint(@PathVariable Long id, @RequestBody RecyclingPoint recyclingPoint) {
        recyclingPoint.setId(id);
        recyclingService.modifyRecyclingPoint(recyclingPoint);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Delete a RecyclingPoint by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecyclingPoint(@PathVariable Long id) {
        recyclingService.deleteRecyclingPoint(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Get all the RecyclingPoints")
    @GetMapping
    public ResponseEntity<List<RecyclingPoint>> searchAllRecyclingPoints() {
        List<RecyclingPoint> recyclingPoints = recyclingService.searchAll();
        return ResponseEntity.ok(recyclingPoints);
    }
}
