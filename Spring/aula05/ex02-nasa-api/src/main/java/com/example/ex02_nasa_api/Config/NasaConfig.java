package com.example.ex02_nasa_api.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NasaConfig {

    @Value("${nasa.api.baseurl}") // vem do application.properties
    private String baseUrl;

    @Bean
    public WebClient creatrWebClient () {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
