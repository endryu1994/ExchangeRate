package com.akybenko.exchangerate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CsvExchangeRateDto {

    private String code;
    private Double rate;
}
