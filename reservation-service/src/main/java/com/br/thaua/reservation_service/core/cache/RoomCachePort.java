package com.br.thaua.reservation_service.core.cache;

import com.br.thaua.reservation_service.domain.Room;

public interface RoomCachePort {
    Room getCacheRoom(String key);
    void putCacheRoom(Room room);
    void updateCacheRoom(Room room);
    void evictRoom(Long roomId);
}
