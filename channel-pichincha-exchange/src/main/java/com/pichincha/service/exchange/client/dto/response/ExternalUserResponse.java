package com.pichincha.service.exchange.client.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalUserResponse {
    private Integer id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
