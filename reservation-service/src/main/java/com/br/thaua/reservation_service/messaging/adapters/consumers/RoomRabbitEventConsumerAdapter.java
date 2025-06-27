package com.br.thaua.reservation_service.messaging.adapters.consumers;

import com.br.thaua.reservation_service.core.cache.RoomCachePort;
import com.br.thaua.reservation_service.core.messaging.consumers.RoomEventConsumerPort;
import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import com.br.thaua.reservation_service.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomRabbitEventConsumerAdapter implements RoomEventConsumerPort {
    private final RoomCachePort roomCachePort;
    private final ReservationRepositoryPort reservationRepositoryPort;

    @RabbitListener(queues = {"room.created.queue", "room.update.queue"})
    @Override
    public void fetchRoomForMessaging(Room room) {
        roomCachePort.putCacheRoom(room);
    }

    @Transactional
    @RabbitListener(queues = "room.deleted.queue")
    @Override
    public void fetchRoomIdDeletedForMessaging(Long roomId) {
        roomCachePort.evictRoom(roomId);
        reservationRepositoryPort.deleteAllByRoomId(roomId);
    }
}
