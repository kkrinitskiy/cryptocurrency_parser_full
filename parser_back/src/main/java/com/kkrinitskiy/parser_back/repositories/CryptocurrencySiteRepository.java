package com.kkrinitskiy.parser_back.repositories;

import com.kkrinitskiy.parser_back.models.CryptocurrencySiteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptocurrencySiteRepository extends JpaRepository<CryptocurrencySiteModel, Long> {
    Optional<CryptocurrencySiteModel> findByName(String name);
}
