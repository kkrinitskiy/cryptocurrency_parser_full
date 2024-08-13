package com.kkrinitskiy.parser_back.parser;

import com.kkrinitskiy.parser_back.dto.CryptocurrencyRateDto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/**
 * Будущий репозиторий
 */
public class TextFileStorage {

    public void store(List<CryptocurrencyRateDto> cryptocurrencyRateDtoList, String siteName){
        try{
            Files.deleteIfExists(Path.of("output/" + siteName + "_output.txt"));
        }catch (IOException e){
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/" + siteName + "_output.txt", false))) {
            for (CryptocurrencyRateDto cryptocurrencyRateDto : cryptocurrencyRateDtoList) {
                    writer.write(cryptocurrencyRateDto.name() + " - " + cryptocurrencyRateDto.currentRate() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
