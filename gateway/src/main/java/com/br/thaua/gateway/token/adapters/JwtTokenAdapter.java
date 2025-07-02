package com.br.thaua.gateway.token.adapters;

import com.br.thaua.gateway.core.token.TokenManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtTokenAdapter implements TokenManager {
    private final PublicKey publicKey;

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return  claimsTFunction.apply(extractAllClaims(token));
    }

    @Override
    public Long extractId(String token) {
        return extractClaims(token, claims -> claims.get("id", Long.class));
    }

    @Override
    public String extractName(String token) {
        return extractClaims(token, claims -> claims.get("name", String.class));
    }

    @Override
    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    @Override
    public List<String> extractRoles(String token) {
        return (List<String>) extractClaims(token, claims -> claims.get("roles"));
    }

    @Override
    public boolean validateToken(String token) {
        Date expiration = extractClaims(token, Claims::getExpiration);
        return (expiration.after(new Date(System.currentTimeMillis())));
    }
}
