package com.br.thaua.room_service.messaging.mappers;

import com.br.thaua.room_service.domain.EventType;
import com.br.thaua.room_service.domain.Room;
import com.br.thaua.room_service.messaging.dto.RoomEventCreatedUpdated;
import com.br.thaua.room_service.messaging.dto.RoomEventDeletedReleased;
import com.br.thaua.room_service.messaging.dto.RoomEventFeedBack;
import com.br.thaua.room_service.messaging.dto.RoomEventLiked;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomEventMapper {
    default <T> T map(EventType eventType, Room room) {
        if(eventType.equals(EventType.ROOM_CREATED)) {
            return (T) new RoomEventCreatedUpdated(EventType.ROOM_CREATED, room.getRoomId(), room.getRoomNumber(), room.getCapacity());
        }

        if(eventType.equals(EventType.ROOM_UPDATED)) {
            return (T) new RoomEventCreatedUpdated(EventType.ROOM_UPDATED, room.getRoomId(), room.getRoomNumber(), room.getCapacity());
        }

        if(eventType.equals(EventType.ROOM_DELETED)) {
            return (T) new RoomEventDeletedReleased(EventType.ROOM_DELETED, room.getRoomId());
        }

        if(eventType.equals(EventType.ROOM_RELEASED)) {
            return (T) new RoomEventDeletedReleased(EventType.ROOM_RELEASED, room.getRoomId());
        }
        return null;

    }

    default <T> T map(EventType eventType, Room room, Long authId) {
        if(eventType.equals(EventType.ROOM_LIKED)) {
            return (T) new RoomEventLiked(EventType.ROOM_LIKED, room.getRoomId(), room.getRoomNumber(), authId);
        }
        return null;
    }

    default RoomEventFeedBack map(EventType eventType, Long authId) {
        return new RoomEventFeedBack(eventType, authId);
    }
}
