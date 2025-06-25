package com.br.thaua.reservation_service.core.messaging.consumers;

import com.br.thaua.reservation_service.domain.Room;

public interface RoomEventConsumerPort {
    void fetchRoomForMessaging(Room room);
    void fetchRoomIdDeletedForMessaging(Long roomId);
}
