package com.br.thaua.reservation_service.cache.adapters.validators;

import com.br.thaua.reservation_service.core.cache.AuthCachePort;
import com.br.thaua.reservation_service.core.cache.validators.AuthCacheValidatorPort;
import com.br.thaua.reservation_service.core.restClient.RestClientPort;
import com.br.thaua.reservation_service.domain.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCacheRedisValidatorPortAdapter implements AuthCacheValidatorPort {
    private final RestClientPort restClientPort;
    private final AuthCachePort authCachePort;

    @Override
    public Auth validateAuthCache(String email, Auth auth) {
        if(auth == null) {
            auth = restClientPort.fetchAuthById(email);
        }

        if(auth != null) {
            authCachePort.putCacheAuthEmailKey(auth);
            return auth;
        }
        throw new RuntimeException("Auth not found");
    }
}
