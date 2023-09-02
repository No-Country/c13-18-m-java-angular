package com.grupo18.nocountry.greenpoint.email;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService service;

    @GetMapping
    public void testEmail(){

    }

}
