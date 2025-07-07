package com.br.thaua.reservation_service.core.cache.validators;

import com.br.thaua.reservation_service.domain.Auth;

public interface AuthCacheValidatorPort {
    void validateAuthCache(Long authId, Auth auth);
}
