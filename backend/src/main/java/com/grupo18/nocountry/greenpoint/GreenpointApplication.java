package com.grupo18.nocountry.greenpoint;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@SpringBootApplication
public class GreenpointApplication {

	public static void main(String[] args) throws IOException {

		Resource resource = new ClassPathResource("log4j.properties");
		PropertyConfigurator.configure(resource.getURL());
		SpringApplication.run(GreenpointApplication.class, args);
	}}