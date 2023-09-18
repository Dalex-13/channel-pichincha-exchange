package com.pichincha.service.expose.web;

import com.pichincha.service.exchange.business.ExchangeService;
import com.pichincha.service.expose.dto.response.ExchangeResponse;
import com.pichincha.service.expose.dto.resquest.ExchangeRequest;
import com.pichincha.service.expose.dto.resquest.ExchangeUpdateRequest;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Completable createExchange(
            @Valid @RequestBody ExchangeRequest exchangeRequest) {
        return exchangeService.createExchange(exchangeRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public Completable updateExchange(
            @NotNull @Positive @PathVariable("id") Long id,
            @Valid @RequestBody ExchangeUpdateRequest exchangeUpdateRequest) {
        return exchangeService.updateExchange(id, exchangeUpdateRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Single<ExchangeResponse> findExchange(@NotNull @Positive @PathVariable("id") Long id) {
        return exchangeService.getExchangeById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/user")
    public Flowable<ExchangeResponse> list(@NotNull @Positive @PathVariable("userId") Integer userId) {
        return exchangeService.listExchangeByUserId(userId);
    }
}
