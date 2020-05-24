package com.akybenko.exchangerate.service;

import com.akybenko.exchangerate.model.ExchangeRate;
import com.google.common.collect.ImmutableMap;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExchangeRatesApiClient {

    private final RestTemplate restTemplate;

    @Value("${exchangeratesapi.latest.url}")
    private String exchangeRatesApiLatestUrl;

    public ExchangeRatesApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRate findExchangeByCurrencyCode(@NonNull String currencyCode) {
        ResponseEntity<ExchangeRate> response = restTemplate.getForEntity(
                exchangeRatesApiLatestUrl, ExchangeRate.class, ImmutableMap.of("code", currencyCode));
        return response.getBody();
    }
}
