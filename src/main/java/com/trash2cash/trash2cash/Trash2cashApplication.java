package com.trash2cash.trash2cash;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot 3 Auth APIs", version = "1.0",
		description = "Auth application documentation", contact = @Contact(name = "Zeeshan Adil")),
		security = {@SecurityRequirement(name = "bearerToken"), @SecurityRequirement(name = "cookie")})
@SecuritySchemes({
		@SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"),
		@SecurityScheme(name = "cookie", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, paramName = "cookie")
})
public class Trash2cashApplication {

	public static void main(String[] args) {
		SpringApplication.run(Trash2cashApplication.class, args);
	}

}
