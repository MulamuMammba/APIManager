package com.mammba.APIManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ApiManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiManagerApplication.class, args);
	}

}
