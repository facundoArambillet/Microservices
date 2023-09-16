package com.example.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return(((exchange, chain) -> {
        if(routeValidator.isSecured.test(exchange.getRequest())) {
            //header contains token or not
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing authorization header");
            }
            String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if(authHeaders != null && authHeaders.startsWith("Bearer ")) {
                authHeaders = authHeaders.substring(7);
                System.out.println(authHeaders);
            }
            try {
                if(!jwtUtil.validateToken(authHeaders)) {
                     throw new RuntimeException("");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException("Unauthorized access to application");
            }
        }
            return chain.filter(exchange);
        }));
    }

    public static class Config {}
}
