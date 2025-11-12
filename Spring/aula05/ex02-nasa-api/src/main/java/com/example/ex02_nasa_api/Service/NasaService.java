package com.example.ex02_nasa_api.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NasaService {

    private final WebClient webClient;

    @Value("${nasa.api.key}") // vem do application.properties
    private String apiKey;

    public NasaService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getApod() {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/planetary/apod")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    
}
