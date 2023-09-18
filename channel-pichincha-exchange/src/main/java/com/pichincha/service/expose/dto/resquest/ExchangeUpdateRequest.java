package com.pichincha.service.expose.dto.resquest;

import com.pichincha.service.exchange.util.enums.CurrencyEnum;
import com.pichincha.service.expose.validator.ValueOfEnumCurrency;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ExchangeUpdateRequest {
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=10, fraction=2)
    @NotNull
    private Double amount;
    @ValueOfEnumCurrency(enumClass = CurrencyEnum.class)
    private String sourceCurrency;
    @ValueOfEnumCurrency(enumClass = CurrencyEnum.class)
    private String targetCurrency;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=10, fraction=2)
    private Double convertedAmount;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 4)
    private Double exchangeRate;
}
