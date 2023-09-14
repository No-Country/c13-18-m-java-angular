package com.grupo18.nocountry.greenpoint.redeemreward;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/redeem/reward")
@RequiredArgsConstructor
public class RedeemRewardController {

    private final RedeemRewardService service;


    @PostMapping
    public ResponseEntity<RedeemResponse> redeem(@Valid @RequestBody RedeemRewardRequest request){
        return ResponseEntity.status(200).body(service.redeem(request));
    }
}
