package com.school.management.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurazione per OpenAPI/Swagger per documentare le API e gestire l'autenticazione JWT.
 */
@Configuration
public class ApiConfig {

    private static final String BEARER_KEY = "BearerAuth";

    /**
     * Configura OpenAPI per includere informazioni sulle API e il supporto per l'autenticazione JWT.
     *
     * @return Un'istanza di OpenAPI configurata.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Esame Fine Corso Java")
                        .version("1.0")
                        .description("Documentazione API per il progetto finale"))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_KEY))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(BEARER_KEY,
                                new SecurityScheme()
                                        .name(BEARER_KEY)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
