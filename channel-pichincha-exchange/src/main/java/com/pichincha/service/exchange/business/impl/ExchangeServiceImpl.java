package com.pichincha.service.exchange.business.impl;

import com.pichincha.service.exchange.business.ExchangeService;
import com.pichincha.service.exchange.business.UserService;
import com.pichincha.service.exchange.business.mapper.ExchangeMapper;
import com.pichincha.service.exchange.client.ExternalServiceClient;
import com.pichincha.service.expose.dto.response.ExchangeResponse;
import com.pichincha.service.expose.dto.resquest.ExchangeRequest;
import com.pichincha.service.expose.dto.resquest.ExchangeUpdateRequest;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExternalServiceClient externalServiceClient;
    private final UserService userService;
    private final ExchangeMapper exchangeMapper;

    @Override
    public Completable createExchange(ExchangeRequest exchangeRequest) {
        return userService.findUserById(exchangeRequest.getUserId())
                .flatMapCompletable(externalUserResponse -> externalServiceClient.createExchange(exchangeRequest));
    }

    @Override
    public Completable updateExchange(Long id, ExchangeUpdateRequest exchangeUpdateRequest) {
        return externalServiceClient.updateExchange(id, exchangeUpdateRequest);

    }

    @Override
    public Flowable<ExchangeResponse> listExchangeByUserId(Integer userId) {
        return userService.findUserById(userId)
                .flatMapPublisher(userResponse ->
                        externalServiceClient.listExchangeByUserId(userId)
                                .map(externalExchangeResponse ->
                                        exchangeMapper.toResponse(externalExchangeResponse, userResponse.getName())));
    }

    @Override
    public Single<ExchangeResponse> getExchangeById(Long id) {

        return externalServiceClient.getExchangeById(id)
                .flatMap(externalExchangeResponse ->
                        userService.findUserById(externalExchangeResponse.getUserId())
                                .map(userResponse ->
                                        exchangeMapper.toResponse(externalExchangeResponse, userResponse.getName())));
    }
}
