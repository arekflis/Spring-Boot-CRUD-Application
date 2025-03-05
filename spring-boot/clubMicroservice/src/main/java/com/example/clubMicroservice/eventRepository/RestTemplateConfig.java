package com.example.clubMicroservice.eventRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate clubRestTemplate(@Value("${players.microservice.url}") String playersURL){
        return new RestTemplateBuilder().rootUri(playersURL).build();
    }
}
