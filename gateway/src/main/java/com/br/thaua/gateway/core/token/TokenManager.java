package com.br.thaua.gateway.core.token;

import java.util.List;

public interface TokenManager {
    Long extractId(String token);
    String extractName(String token);
    String extractEmail(String token);
    List<String> extractRoles(String token);
    boolean validateToken(String token);
}
