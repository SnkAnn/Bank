package com.example.bank.repository;


import com.example.bank.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    Optional<CurrencyRate> findByDateAndCurrencyCode(LocalDate date, String currencyCode);

}

