package com.br.thaua.gateway.core.cache;

public interface GatewayCachePort {
    Object getCacheGateway(String key);
    void putCacheGatewayIdKey(Long id, Object value);
    void putCacheGatewayEmailKey(String email, Object value);
    void evictCacheGateway(String key);
}
