package com.br.thaua.room_service.messaging.adapters.consumers;

import com.br.thaua.room_service.core.messaging.consumers.ReservationEventConsumerPort;
import com.br.thaua.room_service.core.services.RoomServicePort;
import com.br.thaua.room_service.domain.EventType;
import com.br.thaua.room_service.domain.RoomStat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationRabbitEventConsumerAdapter implements ReservationEventConsumerPort {
    private final ObjectMapper objectMapper;
    private final RoomServicePort roomServicePort;

    @RabbitListener(queues = "room.reservation.queue")
    @Override
    public void consumerEvents(String event) {
        JsonNode node = fetchJsonNode(event);
        EventType eventType = EventType.valueOf(node.get("eventType").asText().toUpperCase());
        Long roomId = node.get("roomId").asLong();

        switch (eventType) {
            case RESERVATION_BEGIN -> roomServicePort.updateRoomStat(roomId, RoomStat.BUSY);
            case RESERVATION_FINISHED -> roomServicePort.updateRoomStat(roomId, RoomStat.AVAILABLE);
        }
    }

    public JsonNode fetchJsonNode(String event) {
        try {
            return objectMapper.readTree(event);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
