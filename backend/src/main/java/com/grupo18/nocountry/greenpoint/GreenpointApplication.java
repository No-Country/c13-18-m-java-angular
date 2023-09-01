package com.grupo18.nocountry.greenpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class GreenpointApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GreenpointApplication.class, args);
	}
}