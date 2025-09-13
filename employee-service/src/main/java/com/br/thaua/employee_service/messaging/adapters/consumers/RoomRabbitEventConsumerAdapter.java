package com.br.thaua.employee_service.messaging.adapters.consumers;

import com.br.thaua.employee_service.core.messaging.consumers.RoomEventConsumerPort;
import com.br.thaua.employee_service.core.services.EmployeeServicePort;
import com.br.thaua.employee_service.domain.EventType;
import com.br.thaua.employee_service.domain.FavRoom;
import com.br.thaua.employee_service.messaging.dto.RoomEventLiked;
import com.br.thaua.employee_service.messaging.mappers.RoomEventMapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomRabbitEventConsumerAdapter implements RoomEventConsumerPort {
    private final EmployeeServicePort employeeServicePort;
    private final EventPayloadConverter eventPayloadConverter;
    private final RoomEventMapper mapper;

    @RabbitListener(queues = "employee.room.queue")
    @Override
    public void consumerEvents(String event) {
        JsonNode node = eventPayloadConverter.fetchJsonNode(event);
        EventType eventType = eventPayloadConverter.fetchEventType(node);

        switch (eventType) {
            case ROOM_LIKED -> {
                RoomEventLiked roomEventLiked = eventPayloadConverter.fetchEventClass(RoomEventLiked.class, node);
                roomLiked(mapper.map(roomEventLiked), roomEventLiked.employeeId());
            }

            case ROOM_DELETED -> {
                Long roomId = node.get("roomId").asLong();
                roomDeleted(roomId);
            }

            case ROOM_FEEDBACK -> {
                Long employeeId = node.get("authId").asLong();
                roomFeedBack(employeeId);
            }
        }
    }

    private void roomLiked(FavRoom favRoom, Long employeeId) {
        employeeServicePort.addFavRoom(favRoom);
        employeeServicePort.increaseEmployeeScore(employeeId, 5);
    }

    private void roomDeleted(Long roomId) {
        employeeServicePort.deleteFavRoom(roomId);
    }

    private void roomFeedBack(Long employeeId) {
        employeeServicePort.increaseEmployeeScore(employeeId, 8);
    }

}
