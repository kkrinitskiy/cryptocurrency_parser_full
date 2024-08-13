package com.kkrinitskiy.parser_back.repositories;

import com.kkrinitskiy.parser_back.models.CryptocurrencyRateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptocurrencyRateRepository extends JpaRepository<CryptocurrencyRateModel, Long> {
}
