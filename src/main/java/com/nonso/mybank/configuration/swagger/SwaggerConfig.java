package com.nonso.mybank.configuration.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

    final String securitySchemaName = "bearerAuth";
    @Bean
    public OpenAPI decaPayAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("MyBank API ")
                        .description("MyBank api is a finance api that helps you create a personal account, send money from your account and pay utilities .")
                        .version("1.11")

                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemaName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemaName, new SecurityScheme()
                                .name(securitySchemaName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

    }
}
