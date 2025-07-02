package com.br.thaua.auth_service.core.token;

public interface TokenManagerPort {
    String generateToken(Object payloadData);
}
