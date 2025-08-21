package com.br.thaua.reservation_service.cache.adapters;

import com.br.thaua.reservation_service.core.cache.RoomCachePort;
import com.br.thaua.reservation_service.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RoomCacheRedisAdapter implements RoomCachePort {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Room getCacheRoom(Long key) {
        return (Room) redisTemplate.opsForValue().get(roomKey(key));
    }

    @Override
    public void putCacheRoom(Room room) {
        redisTemplate.opsForValue().set(roomKey(room.getId()), room, Duration.ofMinutes(30));
    }

    @Override
    public void updateCacheRoom(Room room) {
        redisTemplate.opsForValue().set(roomKey(room.getId()), room, Duration.ofMinutes(30));
    }

    @Override
    public void evictRoom(Long roomId) {
        redisTemplate.delete(roomKey(roomId));
    }

    private String roomKey(Long roomId) {
        return "room:" + roomId;
    }
}
