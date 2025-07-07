package com.br.thaua.reservation_service.cache.adapters;

import com.br.thaua.reservation_service.core.cache.AuthCachePort;
import com.br.thaua.reservation_service.domain.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthCacheRedisAdapter implements AuthCachePort {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Auth getCacheAuth(String key) {
        return (Auth) redisTemplate.opsForValue().get(authKey(key));
    }

    @Override
    public void putCacheAuthEmailKey(Auth auth) {
        redisTemplate.opsForValue().set(authKey(auth.getEmail()), auth, Duration.ofMinutes(30));
    }

    @Override
    public void updateCacheAuthEmailKey(Auth auth) {
        redisTemplate.opsForValue().set(authKey(auth.getEmail()), auth.getId(), Duration.ofMinutes(30));
    }

    @Override
    public void evictAuthEmailKey(String email) {
        redisTemplate.delete(authKey(email));
    }

    private String authKey(String authEmail) {
        return "auth:" + authEmail;
    }

    private String authKey(Long id) {return "auth:" + id;}
}
