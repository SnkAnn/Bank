package com.example.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Data
@Entity
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String currencyCode;

    private Double rate;

    public CurrencyRate(String currencyCode, double v) {
        this.currencyCode=currencyCode;
        this.rate=v;
    }

    public CurrencyRate() {

    }
}
