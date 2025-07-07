package com.br.thaua.reservation_service.core.cache;

import com.br.thaua.reservation_service.domain.Auth;

public interface AuthCachePort {
    Auth getCacheAuth(String key);
    void putCacheAuthEmailKey(Auth auth);
    void updateCacheAuthEmailKey(Auth auth);
    void evictAuthEmailKey(String email);
}
