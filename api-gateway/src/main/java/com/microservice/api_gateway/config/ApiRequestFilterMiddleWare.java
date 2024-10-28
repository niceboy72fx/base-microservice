package com.microservice.api_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class ApiRequestFilterMiddleWare extends AbstractGatewayFilterFactory<ApiRequestFilterMiddleWare.Config> {

    @Autowired
    private
}
