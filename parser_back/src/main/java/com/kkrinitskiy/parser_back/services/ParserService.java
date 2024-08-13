package com.kkrinitskiy.parser_back.services;

import com.kkrinitskiy.parser_back.dto.CryptocurrencyRateDto;
import com.kkrinitskiy.parser_back.models.CryptocurrencyRateModel;
import com.kkrinitskiy.parser_back.models.CryptocurrencySiteModel;
import com.kkrinitskiy.parser_back.parser.cryptoSiteParsers.CryptoSiteParser;
import com.kkrinitskiy.parser_back.repositories.CryptocurrencyRateRepository;
import com.kkrinitskiy.parser_back.repositories.CryptocurrencySiteRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@EnableScheduling
@Service
@RequiredArgsConstructor
public class ParserService {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final List<CryptoSiteParser> parsers;
    private final CryptocurrencySiteRepository siteRepository;
    private final CryptocurrencyRateRepository rateRepository;
    @PersistenceContext
    private EntityManager entityManager;


    /**
     *  TODO: ДОБАВИТЬ МАППЕР!!!
     */
    @Transactional
    @Scheduled(fixedRateString = "${parser.waiting-time-in-millis}")
    public void parseAndSaveToRepository() {

        entityManager.createNativeQuery("DROP TABLE IF EXISTS rates").executeUpdate();
        entityManager.createNativeQuery(
                "CREATE TABLE rates (" +
                        "id BIGINT PRIMARY KEY, " +
                        "name VARCHAR(255) NOT NULL, " +
                        "rate VARCHAR(255) NOT NULL, " +
                        "site_id BIGINT, " +
                        "FOREIGN KEY (site_id) REFERENCES sites(id))"
        ).executeUpdate();

        List<CompletableFuture<Void>> futures = parsers.stream()
                .map(parser -> CompletableFuture.runAsync(() -> {
                    parser.getCryptocurrency();

                    List<CryptocurrencyRateDto> cryptocurrencyList = parser.getCryptocurrencyList();

                    cryptocurrencyList.stream()
                            .map(dto -> {
                                CryptocurrencyRateModel rate = new CryptocurrencyRateModel();
                                rate.setName(dto.name());
                                rate.setRate(dto.currentRate());
                                rate.setSite(siteRepository.findByName(dto.siteName()).orElse(null));
                                return rate;
                            })
                            .forEach(rateRepository::save);
                }))
                .toList();

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        try {
            allOf.get();
            log.info("Rates parsed and saved to database.");
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error occurred while parsing rates: {}", e.getMessage());
        }
    }

    @PostConstruct
    public void pullSitesToRepository() {
        for (CryptoSiteParser parser : parsers) {
            if(siteRepository.findByName(parser.getSiteName()).isPresent()) {
                continue;
            }
            CryptocurrencySiteModel site = new CryptocurrencySiteModel();
            site.setName(parser.getSiteName());
            site.setUrl(parser.getLink());
            CryptocurrencySiteModel save = siteRepository.save(site);
            log.info("site saved {}", save);
        }
    }
}
