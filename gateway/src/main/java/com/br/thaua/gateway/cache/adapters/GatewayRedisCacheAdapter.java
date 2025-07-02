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
    public void putCacheGateway(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(30));
    }

    @Override
    public void evictCacheGateway(String key) {
        redisTemplate.delete(key);
    }
}
