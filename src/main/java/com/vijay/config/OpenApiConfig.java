package com.vijay.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${app.version}")
    private static String version;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("StudentRegistration API")
                .version(version).description("StudentRegistration service to manage school Student Record"));
    }
}

