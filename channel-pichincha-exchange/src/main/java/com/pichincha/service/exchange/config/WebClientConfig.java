package com.pichincha.service.exchange.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${gorest.service.base-url}")
    private String gorestServiceBaseUrl;

    @Value("${api-support.service.base-url}")
    private String apiSupportServiceBaseUrl;

    @Bean
    public WebClient gorestWebClientBuilder() {
        return WebClient.builder().baseUrl(gorestServiceBaseUrl).build();
    }

    @Bean
    public WebClient apiSupportWebClientBuilder() {
        return WebClient.builder().baseUrl(apiSupportServiceBaseUrl).build();
    }
}
