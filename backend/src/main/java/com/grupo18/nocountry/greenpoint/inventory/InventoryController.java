package com.grupo18.nocountry.greenpoint.inventory;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService service;


    @PutMapping("/reward/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody StockUpdateDTO request, @PathVariable Long id){
            service.updateStock(id,request);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
