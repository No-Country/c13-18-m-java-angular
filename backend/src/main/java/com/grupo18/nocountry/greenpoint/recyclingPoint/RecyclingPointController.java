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
        try {
            recyclingService.createRecyclingPoint(recyclingPoint);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get a RecyclingPoint by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> searchRecyclingPoint(@Parameter(description = "ID of RecyclingPoint to be searched") @PathVariable long id) {
        try {
            Optional<RecyclingPoint> recyclingPoint = recyclingService.searchRecyclingPoint(id);
            return recyclingPoint.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Modify a RecyclingPoint")
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyRecyclingPoint(@PathVariable Long id, @RequestBody RecyclingPoint recyclingPoint) {
        try {
            recyclingPoint.setId(id);
            recyclingService.modifyRecyclingPoint(recyclingPoint);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete a RecyclingPoint by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecyclingPoint(@PathVariable Long id) {
        try {
            recyclingService.deleteRecyclingPoint(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get all the RecyclingPoints")
    @GetMapping
    public ResponseEntity<List<RecyclingPoint>> searchAllRecyclingPoints() {
        try {
            List<RecyclingPoint> recyclingPoints = recyclingService.searchAll();
            return ResponseEntity.ok(recyclingPoints);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}