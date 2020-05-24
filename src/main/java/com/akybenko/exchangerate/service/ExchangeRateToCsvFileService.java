package com.akybenko.exchangerate.service;

import com.akybenko.exchangerate.model.CsvExchangeRateDto;
import com.akybenko.exchangerate.model.ExchangeRate;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ExchangeRateToCsvFileService {

    private final ExchangeRatesApiClient client;
    private final CsvExchangeRateWriter writer;
    private final CsvExchageRatesConverter converter;

    public ExchangeRateToCsvFileService(ExchangeRatesApiClient client,
                                        CsvExchangeRateWriter writer,
                                        CsvExchageRatesConverter converter) {
        this.client = client;
        this.writer = writer;
        this.converter = converter;
    }

    public void toCsvFile(@NonNull String currencyCode) {
        String filename = String.format("ExchangeRate_%s.csv", currencyCode);
        ExchangeRate exchangeRateByCurrencyCode = client.findExchangeByCurrencyCode(currencyCode);
        final Set<CsvExchangeRateDto> data = converter.toCsvExchangeRateDto(exchangeRateByCurrencyCode);
        writer.write(filename, data);
    }
}
