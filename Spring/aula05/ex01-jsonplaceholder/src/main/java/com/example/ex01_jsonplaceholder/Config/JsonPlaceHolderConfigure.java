package com.example.ex01_jsonplaceholder.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class JsonPlaceHolderConfigure {
    
    @Bean
    public WebClient CreateWebClient() {
        return WebClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build();
    }

}
