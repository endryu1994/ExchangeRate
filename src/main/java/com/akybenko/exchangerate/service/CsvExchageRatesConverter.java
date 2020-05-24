package com.akybenko.exchangerate.service;

import com.akybenko.exchangerate.model.CsvExchangeRateDto;
import com.akybenko.exchangerate.model.ExchangeRate;
import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CsvExchageRatesConverter {

    public Set<CsvExchangeRateDto> toCsvExchangeRateDto(@NonNull ExchangeRate rate) {
        return rate.getRates()
                .entrySet()
                .stream()
                .map(entry -> new CsvExchangeRateDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.collectingAndThen(Collectors.toSet(), ImmutableSet::copyOf));
    }
}
