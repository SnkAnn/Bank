package com.example.bank.service;

import com.example.bank.model.CurrencyRate;

import java.time.LocalDate;
import java.util.Optional;

public interface CurrencyRateService {

         Optional<CurrencyRate> getRateByDateAndCurrencyCode(LocalDate date, String currencyCode) ;
         CurrencyRate saveRate(CurrencyRate rate);


    boolean loadRatesForDate(LocalDate date);
}
