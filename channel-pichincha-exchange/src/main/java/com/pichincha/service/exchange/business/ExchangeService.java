package com.pichincha.service.exchange.business;

import com.pichincha.service.expose.dto.response.ExchangeResponse;
import com.pichincha.service.expose.dto.resquest.ExchangeRequest;
import com.pichincha.service.expose.dto.resquest.ExchangeUpdateRequest;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface ExchangeService {

    Completable createExchange(ExchangeRequest exchangeRequest);
    Completable updateExchange(Long id, ExchangeUpdateRequest exchangeUpdateRequest);
    Flowable<ExchangeResponse> listExchangeByUserId(Integer userId);
    Single<ExchangeResponse> getExchangeById(Long id);
}
