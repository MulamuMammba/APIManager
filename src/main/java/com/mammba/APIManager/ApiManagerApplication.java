package com.mammba.APIManager;

import com.mammba.APIManager.Repository.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ApiManagerApplication {

	public static void main(String[] args) {
		Database.createAccountTable();
		Database.createApiTable();
		Database.createEndpointsTable();
		SpringApplication.run(ApiManagerApplication.class, args);
	}

}
