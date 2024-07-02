package com.example.bank.controller;

import com.example.bank.model.CurrencyRate;
import com.example.bank.service.CurrencyRateService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@AllArgsConstructor
public class CurrencyRateControllerIntegrationTests {
    private final CurrencyRateService currencyRateService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRateByDateAndCurrencyCode() throws Exception {
        // Arrange
        LocalDate date = LocalDate.now();
        String currencyCode = "USD";
        CurrencyRate mockRate = new CurrencyRate(currencyCode, 2.0);

        // Mock service behavior

        when(currencyRateService.getRateByDateAndCurrencyCode(date, currencyCode)).thenReturn(Optional.of(mockRate));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rate")
                        .param("date", date.toString())
                        .param("currencyCode", currencyCode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyCode").value(currencyCode))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(2.0));
    }
}
