package com.br.thaua.auth_service.token.adapters;

import com.br.thaua.auth_service.core.token.TokenManager;
import com.br.thaua.auth_service.domain.Auth;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenJwtAdapter implements TokenManager {
    private final PrivateKey privateKey;

    public String generateToken(Object payloadData) {
        Auth auth = (Auth) payloadData;
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", auth.getId());
        claims.put("name", auth.getName());
        claims.put("roles", auth.getRoles());

        return Jwts.builder()
                .claims(claims)
                .subject(auth.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60))
                .signWith(privateKey)
                .compact();
    }
}
