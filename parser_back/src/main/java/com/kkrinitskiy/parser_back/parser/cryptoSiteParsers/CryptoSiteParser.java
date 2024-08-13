package com.kkrinitskiy.parser_back.parser.cryptoSiteParsers;

import com.kkrinitskiy.parser_back.dto.CryptocurrencyRateDto;

import java.util.List;


public interface CryptoSiteParser extends Runnable{

    String getSiteName();
    String getLink();
    void getCryptocurrency();
    List<CryptocurrencyRateDto> getCryptocurrencyList();
    @Override
    default void run(){
        getCryptocurrency();
    };
}
