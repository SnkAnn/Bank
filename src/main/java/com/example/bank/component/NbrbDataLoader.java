package com.example.bank.component;
import com.example.bank.model.CurrencyRate;
import com.example.bank.service.CurrencyRateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class NbrbDataLoader {

    private final CurrencyRateService service;


    @Scheduled(cron = "0 0 0 * * ?")
    public void loadRates() {
        RestTemplate restTemplate = new RestTemplate();
        LocalDate date = LocalDate.now();
        String url = "https://www.nbrb.by/api/exrates/rates?ondate=" + date.toString() + "&periodicity=0";

        try {
            CurrencyRate[] rates = restTemplate.getForObject(url, CurrencyRate[].class);
            if (rates != null) {
                Arrays.stream(rates).forEach(service::saveRate);
            }
        } catch (Exception e) {
             e.printStackTrace();
          }
    }
}

