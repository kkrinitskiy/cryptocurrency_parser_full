package com.kkrinitskiy.parser_back.dto;

import java.util.List;

public record CryptocurrencySiteDto(String siteName, String siteUrl, List<CryptocurrencyRateDto> cryptocurrencies) {
}
