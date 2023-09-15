package com.example.gatewayservice.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
//public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
//    private Builder webClientBuilder;
//    public AuthFilter(Builder webClientBuilder) {
//        super(Config.class);
//        this.webClientBuilder = webClientBuilder;
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return ((((exchange, chain) -> {
//            if(!exchange.getResponse().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                return onError(exchange, HttpStatus.BAD_REQUEST);
//            }
//            String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//            String[] chuncks = tokenHeader.split(" ");
//            if(chuncks.length !=2 || !chuncks[0].equals("Bearer")) {
//                return onError(exchange, HttpStatus.BAD_REQUEST);
//            }
//        })))
//    }
//
//    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
//        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(status);
//        return response.setComplete();
//    }
//    public static class Config {}
//}
