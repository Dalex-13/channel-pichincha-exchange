package com.pichincha.service.exchange.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CurrencyEnum {

    USD("United States Dollar", "$", "The official currency of the United States."),
    PEN("Peruvian Sol", "S/.", "The official currency of Peru.");

    private final String name;
    private final String symbol;
    private final String description;
}
