package com.pichincha.service.exchange.client;

import com.pichincha.service.exchange.client.dto.response.ExternalExchangeResponse;
import com.pichincha.service.exchange.client.dto.response.ExternalUserResponse;
import com.pichincha.service.exchange.config.WebClientConfig;
import com.pichincha.service.expose.dto.resquest.ExchangeRequest;
import com.pichincha.service.expose.dto.resquest.ExchangeUpdateRequest;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ExternalServiceClient {

    private final WebClientConfig webClientConfig;

    @Value("${gorest.service.token}")
    private String gorestServiceToken;

    public Maybe<ExternalUserResponse> getUserById(Integer id) {
        return Maybe.fromPublisher(
                webClientConfig.gorestWebClientBuilder()
                        .get()
                        .uri("/public/v2/users/{id}", id)
                        .header("Authorization", "Bearer " + gorestServiceToken)
                        .retrieve()
                        .bodyToMono(ExternalUserResponse.class)
        );
    }


    public Completable createExchange(ExchangeRequest externalExchangeRequest) {
        return Completable.fromPublisher(
                webClientConfig.apiSupportWebClientBuilder()
                        .post()
                        .uri("/exchange")
                        .bodyValue(externalExchangeRequest)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(String.class)
                                .flatMap(errorMessage ->
                                        Mono.error(new ResponseStatusException(response.statusCode(), errorMessage))))
                        .onStatus(HttpStatus::is5xxServerError, response -> response.bodyToMono(String.class)
                                .flatMap(errorMessage ->
                                        Mono.error(new ResponseStatusException(response.statusCode(), errorMessage))))
                        .bodyToMono(Void.class)
        );
    }

    public Completable updateExchange(Long id, ExchangeUpdateRequest externalExchangeUpdateRequest) {
        return Completable.fromPublisher(
                webClientConfig.apiSupportWebClientBuilder()
                        .patch()
                        .uri("/exchange/{id}", id)
                        .bodyValue(externalExchangeUpdateRequest)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(String.class)
                                .flatMap(errorMessage ->
                                        Mono.error(new ResponseStatusException(response.statusCode(), errorMessage))))
                        .onStatus(HttpStatus::is5xxServerError, response -> response.bodyToMono(String.class)
                                .flatMap(errorMessage ->
                                        Mono.error(new ResponseStatusException(response.statusCode(), errorMessage))))
                        .bodyToMono(Void.class)
        );
    }


    public Single<ExternalExchangeResponse> getExchangeById(Long id) {
        return Single.fromPublisher(
                webClientConfig.apiSupportWebClientBuilder()
                        .get()
                        .uri("/exchange/{id}", id)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(String.class)
                                .flatMap(errorMessage ->
                                        Mono.error(new ResponseStatusException(response.statusCode(), errorMessage))))
                        .onStatus(HttpStatus::is5xxServerError, response -> response.bodyToMono(String.class)
                                .flatMap(errorMessage ->
                                        Mono.error(new ResponseStatusException(response.statusCode(), errorMessage))))
                        .bodyToMono(ExternalExchangeResponse.class)
        );
    }


    public Flowable<ExternalExchangeResponse> listExchangeByUserId(Integer userId) {
        return Flowable.fromPublisher(
                webClientConfig.apiSupportWebClientBuilder()
                        .get()
                        .uri("/exchange/{userId}/user", userId)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(String.class)
                                .flatMap(errorMessage ->
                                        Mono.error(new ResponseStatusException(response.statusCode(), errorMessage))))
                        .onStatus(HttpStatus::is5xxServerError, response -> response.bodyToMono(String.class)
                                .flatMap(errorMessage ->
                                        Mono.error(new ResponseStatusException(response.statusCode(), errorMessage))))
                        .bodyToFlux(ExternalExchangeResponse.class)
        );
    }

}
