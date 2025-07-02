package com.br.thaua.gateway.core.cache;

public interface GatewayCachePort {
    Object getCacheGateway(String key);
    void putCacheGateway(String key, Object value);
    void evictCacheGateway(String key);
}
