package org.edu.fabs.javaparkinglot.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        openIdConnectUrl = "openIdConnect",
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDetail() {

        return new OpenAPI()
                .info(new Info().title("Parking Rest API")
                        .contact(new io.swagger.v3.oas.models.info.Contact().name("Backend Student").email("student@email.com"))
                        .description("Spring Boot application for Parking Lot")
                        .version("0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation());
    }

}
