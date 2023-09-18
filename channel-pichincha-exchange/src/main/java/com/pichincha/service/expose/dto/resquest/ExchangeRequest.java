package com.pichincha.service.expose.dto.resquest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ExchangeRequest extends ExchangeUpdateRequest{
    @NotNull
    @Positive
    private Integer userId;
}
