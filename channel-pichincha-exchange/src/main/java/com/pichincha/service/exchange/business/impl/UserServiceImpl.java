package com.pichincha.service.exchange.business.impl;

import com.pichincha.service.exchange.business.UserService;
import com.pichincha.service.exchange.client.ExternalServiceClient;
import com.pichincha.service.exchange.client.dto.response.ExternalUserResponse;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ExternalServiceClient externalServiceClient;

    @Override
    public Single<ExternalUserResponse> findUserById(Integer userId) {
        return externalServiceClient.getUserById(userId)
                .switchIfEmpty(Single.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario");
                }));
    }
}
