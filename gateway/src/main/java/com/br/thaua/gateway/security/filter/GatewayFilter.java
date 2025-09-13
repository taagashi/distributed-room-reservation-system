package com.br.thaua.gateway.security.filter;

import com.br.thaua.gateway.core.cache.GatewayCachePort;
import com.br.thaua.gateway.core.token.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GatewayFilter implements WebFilter {
    private final TokenManager tokenManager;
    private final GatewayCachePort gatewayCachePort;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String token = null;

        if(authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }

        if(token != null && tokenManager.validateToken(token)) {
            Long id = tokenManager.extractId(token);
            String name = tokenManager.extractName(token);
            String email = tokenManager.extractEmail(token);
            List<String> roles = tokenManager.extractRoles(token);

            if(gatewayCachePort.getCacheGateway(generateKeyForGatewayCache(id)) != null || gatewayCachePort.getCacheGateway(generateKeyForGatewayCache(email)) != null) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatusCode.valueOf(401));
                exchange = exchange.mutate().response(response).build();
                return chain.filter(exchange);
            }

            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("x-user-id", String.valueOf(id))
                    .header("x-user-name", name)
                    .header("x-user-email", email)
                    .header("x-user-roles", String.join(",", roles))
                    .header("x-signature-gateway", "signed-by-gateway")
                    .build();

            List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
            exchange = exchange.mutate().request(mutatedRequest).build();
            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));


        }
        ServerHttpRequest mutatedRequest = request.mutate()
                .header("x-signature-gateway", "signed-by-gateway")
                .build();
        exchange = exchange.mutate().request(mutatedRequest).build();
        return chain.filter(exchange);
    }

    private String generateKeyForGatewayCache(Long id) {
        return "revoked:" + id;
    }

    private String generateKeyForGatewayCache(String email) {return "revoked:" + email;}

}
