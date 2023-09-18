package com.pichincha.service.exchange.business.mapper;

import com.pichincha.service.exchange.client.dto.response.ExternalExchangeResponse;
import com.pichincha.service.exchange.util.enums.CurrencyEnum;
import com.pichincha.service.expose.dto.response.ExchangeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, CurrencyEnum.class})
public interface ExchangeMapper {
    ExchangeResponse toResponse(ExternalExchangeResponse externalExchangeResponse, String userName);
}
