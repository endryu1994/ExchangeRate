package com.akybenko.exchangerate.service;

import com.akybenko.exchangerate.model.CsvExchangeRateDto;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Cleanup;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

@Component
public class CsvExchangeRateWriter {

    private final CsvMapper csvMapper = new CsvMapper();
    private final CsvSchema schema =
            csvMapper.schemaFor(CsvExchangeRateDto.class).withHeader().sortedBy("code", "rate");

    public void write(@NonNull String filename, @NonNull Set<CsvExchangeRateDto> data) {
        try {
            @Cleanup Writer writer = new PrintWriter(new FileWriter(filename));
            doWrite(writer, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doWrite(@NonNull Writer writer, @NonNull Set<CsvExchangeRateDto> data) throws IOException {
        csvMapper.writer().with(schema).writeValues(writer).writeAll(data);
    }
}
