package com.example.bank.service.Impl;

import com.example.bank.model.CurrencyRate;
import com.example.bank.repository.CurrencyRateRepository;
import com.example.bank.service.CurrencyRateService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CurrencyRateRepository repository;

    public boolean loadRatesForDate(LocalDate date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.nbrb.by/api/exrates/rates?ondate=" + date.toString() + "&periodicity=0";
        try {
            CurrencyRate[] rates = restTemplate.getForObject(url, CurrencyRate[].class);
            if (rates != null) {
                repository.saveAll(Arrays.asList(rates));
                return true;
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
        }
        return false;
    }

    public Optional<CurrencyRate> getRateByDateAndCurrencyCode(LocalDate date, String currencyCode) {
        return repository.findByDateAndCurrencyCode(date, currencyCode);
    }
    @Override
    public CurrencyRate saveRate(CurrencyRate rate) {
        return repository.save(rate);
    }


}
