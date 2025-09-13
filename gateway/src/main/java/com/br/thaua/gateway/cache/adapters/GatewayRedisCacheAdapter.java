package com.br.thaua.gateway.cache.adapters;

import com.br.thaua.gateway.core.cache.GatewayCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class GatewayRedisCacheAdapter implements GatewayCachePort {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Object getCacheGateway(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void putCacheGatewayIdKey(Long id, Object value) {
        redisTemplate.opsForValue().set(revokedAuthByIdKey(id), value, Duration.ofMinutes(30));
    }

    @Override
    public void putCacheGatewayEmailKey(String email, Object value) {
        redisTemplate.opsForValue().set(revokedAuthByEmailKey(email), value, Duration.ofMinutes(30));
    }

    @Override
    public void evictCacheGateway(String key) {
        redisTemplate.delete(key);
    }

    private String revokedAuthByIdKey(Long id) {
        return "revoked:" + id;
    }

    private String revokedAuthByEmailKey(String email) {
        return "revoked:" + email;
    }
}
