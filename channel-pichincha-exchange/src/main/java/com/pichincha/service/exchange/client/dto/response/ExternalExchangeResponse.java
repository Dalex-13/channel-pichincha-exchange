package com.pichincha.service.exchange.client.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExternalExchangeResponse {
    private Integer userId;
    private Double amount;
    private String sourceCurrency;
    private String targetCurrency;
    private Double convertedAmount;
    private Double exchangeRate;
    private LocalDateTime createdAt;
}
