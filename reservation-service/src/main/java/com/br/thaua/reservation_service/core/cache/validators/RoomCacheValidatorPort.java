package com.br.thaua.reservation_service.core.cache.validators;

import com.br.thaua.reservation_service.domain.Room;

public interface RoomCacheValidatorPort {
    Room validateRoomCache(Long roomId, Room room);
}
