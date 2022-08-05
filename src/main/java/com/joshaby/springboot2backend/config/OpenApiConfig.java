package com.joshaby.springboot2backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot 2 Backend")
                        .description("Repositório do backend do curso [Spring Boot, Hibernate, REST, Ionic, JWT, S3, " +
                                "MySQL, MongoDB](https://www.udemy.com/course/spring-boot-ionic/) na Udemy por Nélio Alves")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("José Henrique")
                                .url("https://github.com/Joshaby")
                                .email("josehenriquebrito55@gmail.com"))
                        .termsOfService("TOC")
                        .license(new License()
                                .name("GNU GPL v2")
                                .url("https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html")))
                .components(
                        new Components()
                                .addSecuritySchemes("bearer-key",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer").bearerFormat("JWT")));
    }
}
