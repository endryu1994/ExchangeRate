package com.akybenko.exchangerate;

import com.akybenko.exchangerate.service.ExchangeRateToCsvFileService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExchangeRateApplication implements CommandLineRunner {

    @Autowired
    private ExchangeRateToCsvFileService service;

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRateApplication.class, args);
    }

    @Override
    public void run(String... args) {
        service.toCsvFile(args[0].trim());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
    }
}
