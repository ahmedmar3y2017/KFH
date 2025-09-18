package com.kfh.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// JWT Swagger Auth
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student-Course API")
                        .description("API documentation for Student and Course management with JWT security")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Ahmed Marey")
                                .email("ahmed.marey@isoft.ae")
                                .url("https://www.linkedin.com/in/ahmed-m-58b834a3/"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))

                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearer-jwt",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
                        .addList("bearer-jwt"));
    }


}
