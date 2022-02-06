package com.mycompany.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Spring Boot Starter Application for running this as Spring Boot Application.
 * 
 * @author Swamy
 * @version 1.0
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Contacts API", version = "1.0", description = "Contacts Information"))
public class SpringBootAddressBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAddressBookApplication.class, args);
	}
}