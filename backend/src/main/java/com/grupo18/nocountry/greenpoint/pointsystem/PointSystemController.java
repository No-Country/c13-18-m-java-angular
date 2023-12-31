package com.grupo18.nocountry.greenpoint.pointsystem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PointSystemController {

    private final PointSystemService service;


    @PostMapping("/recycle")
    public ResponseEntity<RecycleResponse> recycle(@Valid @RequestBody RecycleRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.recycle(request));

    }

    @PostMapping("/redeem")
    public ResponseEntity<?> recycle(@RequestBody RedeemRequest request){
        service.redeem(request);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/transactions/user/{id}")
    public ResponseEntity<Page<TransactionHistory>> getUserTransactions(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserTransactionHistory(pageable,id));
    }

    @GetMapping("/transactions")
    public ResponseEntity<Page<TransactionHistory>> getAllTransactions(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllTransactions(pageable));
    }

    @GetMapping("/details")
    public ResponseEntity<DetailsResponseDTO> getReciclyngDetails(@RequestParam String code ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getDetailsByCode(code));
    }
}
