package com.br.thaua.reservation_service.messaging.adapters.consumers;

import com.br.thaua.reservation_service.core.cache.RoomCachePort;
import com.br.thaua.reservation_service.core.messaging.consumers.RoomEventConsumerPort;
import com.br.thaua.reservation_service.core.repository.ReservationRepositoryPort;
import com.br.thaua.reservation_service.domain.RoomEventType;
import com.br.thaua.reservation_service.messaging.dto.consumer.RoomDeletedEventConsumer;
import com.br.thaua.reservation_service.messaging.dto.consumer.RoomEventConsumer;
import com.br.thaua.reservation_service.messaging.mappers.RoomEventMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomRabbitEventConsumerAdapter implements RoomEventConsumerPort {
    private final RoomCachePort roomCachePort;
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final ObjectMapper objectMapper;
    private final RoomEventMapper roomEventMapper;

    @RabbitListener(queues = {"reservation.room.queue"})
    @Override
    public void consumerRoomEvent(String event) {
        try {
            JsonNode node = fetchJsonNode(event);
            RoomEventType eventType = RoomEventType.valueOf(node.get("eventType").asText());

            switch (eventType) {
                case RoomEventType.ROOM_CREATED:
                    RoomEventConsumer roomCreated = objectMapper.treeToValue(node, RoomEventConsumer.class);
                    roomCreatedEvent(roomCreated);
                    break;

                case RoomEventType.ROOM_UPDATED:
                    RoomEventConsumer roomUpdated = objectMapper.treeToValue(node, RoomEventConsumer.class);
                    roomUpdatedEvent(roomUpdated);
                    break;

                case RoomEventType.ROOM_DELETED:
                    RoomDeletedEventConsumer roomDeleted = objectMapper.treeToValue(node, RoomDeletedEventConsumer.class);
                    roomDeletedEvent(roomDeleted);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void roomCreatedEvent(RoomEventConsumer roomCreated) {
        roomCachePort.putCacheRoom(roomEventMapper.map(roomCreated));
    }

    private void roomUpdatedEvent(RoomEventConsumer roomUpdated) {
        roomCachePort.updateCacheRoom(roomEventMapper.map(roomUpdated));
        reservationRepositoryPort.updateRoomNumberByRoomId(roomUpdated.id(), roomUpdated.roomNumber());
    }

    private void roomDeletedEvent(RoomDeletedEventConsumer roomDeleted) {
        roomCachePort.evictRoom(roomDeleted.id());
        reservationRepositoryPort.deleteAllByRoomId(roomDeleted.id());
    }

    private JsonNode fetchJsonNode(String event) throws JsonProcessingException {
        return objectMapper.readTree(event);
    }
}
