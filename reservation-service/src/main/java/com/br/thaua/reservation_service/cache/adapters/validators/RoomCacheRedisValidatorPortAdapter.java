package com.br.thaua.reservation_service.cache.adapters.validators;

import com.br.thaua.reservation_service.core.cache.RoomCachePort;
import com.br.thaua.reservation_service.core.cache.validators.RoomCacheValidatorPort;
import com.br.thaua.reservation_service.core.restClient.RestClientPort;
import com.br.thaua.reservation_service.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomCacheRedisValidatorPortAdapter implements RoomCacheValidatorPort {
    private final RestClientPort restClientPort;
    private final RoomCachePort roomCachePort;

    @Override
    public void validateRoomCache(Long roomId, Room room) {
        if(room == null) {
            room = restClientPort.fetchRoomById(roomId);
        }

        if(room != null) {
            roomCachePort.putCacheRoom(room);
            return;
        }
        throw new RuntimeException("Room not found");
    }
}
