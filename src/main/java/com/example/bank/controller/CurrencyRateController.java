package com.example.bank.controller;
import com.example.bank.model.CurrencyRate;
import com.example.bank.service.CurrencyRateService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CurrencyRateController {

    private final CurrencyRateService service;

    @GetMapping("/load")
    public ResponseEntity<String> loadRates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        boolean success = service.loadRatesForDate(date);
        if (success) {
            return ResponseEntity.ok("Data loaded successfully for date: " + date);
        } else {
            return ResponseEntity.status(500).body("Failed to load data for date: " + date);
        }
    }

    @GetMapping
    public ResponseEntity<CurrencyRate> getRate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String currencyCode) {
        return service.getRateByDateAndCurrencyCode(date, currencyCode)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

