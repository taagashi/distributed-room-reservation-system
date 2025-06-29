package com.br.thaua.auth_service.core.token;

public interface TokenManager {
    String generateToken(Object payloadData);
}
