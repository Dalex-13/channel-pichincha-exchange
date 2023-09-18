package com.pichincha.service.exchange.business;

import com.pichincha.service.exchange.client.dto.response.ExternalUserResponse;
import io.reactivex.rxjava3.core.Single;

public interface UserService {

    Single<ExternalUserResponse> findUserById(Integer userId);
}
