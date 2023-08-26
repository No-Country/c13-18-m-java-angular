package com.grupo18.nocountry.greenpoint.auth.token;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class RegisterTokenController {

    private final RegisterTokenService tokenService;

    @PostMapping
    public ResponseEntity<HttpStatus> confirmToken(@RequestBody ConfirmTokenRequest token) {
        tokenService.confirmToken(token);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
