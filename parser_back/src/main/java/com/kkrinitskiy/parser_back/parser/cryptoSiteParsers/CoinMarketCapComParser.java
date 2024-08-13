package com.kkrinitskiy.parser_back.parser.cryptoSiteParsers;

import com.kkrinitskiy.parser_back.dto.CryptocurrencyRateDto;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  Данный класс парсит страницу <a href="https://coinmarketcap.com/">coinmarketcap.com</a>
 *  Создает и хранит объекты Cryptocurrency
 */
@Component
public class CoinMarketCapComParser implements CryptoSiteParser {
    private final List<CryptocurrencyRateDto> cryptocurrencyRateDtoList = new ArrayList<>();
    @Getter
    private final String link = "https://coinmarketcap.com/";
    private final String siteName = "coinmarketcap.com";


    /**
     * Метод с помощью библиотеки Jsoup создает соединение с сайтом,
     * получает элемент tbody, обращается к дочерним элементам tr,
     * из каждого построчно получает текстовые значения колонок,
     * отсеивает пустые и создает из полученных данных объекты
     * Cryptocurrency
     */
    public void getCryptocurrency() {
        try {
            Document doc = Jsoup.connect(link).get();
            Objects.requireNonNull(doc.select("tbody").first())
                    .children()
                    .stream()
                    .map(e -> {
                        Elements children = e.children();
                        Elements p = children.get(2).select("p.sc-71024e3e-0.ehyBa-d");
                        p.addAll(children.get(3).select("span:not([class])"));
                        return p;
                    })
                    .filter(e -> !e.isEmpty())
                    .forEach(e ->
                            cryptocurrencyRateDtoList.add(
                                    new CryptocurrencyRateDto(
                                            e.get(0).text(),
                                            e.get(1).text().replace(",",""),
                                            siteName)
                            )
                    );
        } catch (IOException e) {
            throw new RuntimeException("Соединение не удалось " + link, e);
        } catch (NullPointerException e) {
            throw new RuntimeException("tbody = null " + link, e);
        }
    }



    @Override
    public String getSiteName() {
        return siteName;
    }

    @Override
    public List<CryptocurrencyRateDto> getCryptocurrencyList() {
        return List.copyOf(cryptocurrencyRateDtoList);
    }
}
