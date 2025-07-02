package com.br.thaua.gateway.security.config;

import com.br.thaua.gateway.security.filter.GatewayFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final GatewayFilter gatewayFilter;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .addFilterBefore(gatewayFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/v1/auth/delete/**").authenticated()
                        .pathMatchers("/api/v1/auth/account/**").authenticated()
                        .pathMatchers("/api/v1/auth/update/**").authenticated()
                        .pathMatchers("/api/v1/employee/**").hasRole("EMPLOYEE")
                        .pathMatchers("/api/v1/reservation/**").hasRole("EMPLOYEE")
                        .pathMatchers("/api/v1/room/**").hasRole("ADMIN")
                        .anyExchange().permitAll()
                )
                .build();
    }
}
