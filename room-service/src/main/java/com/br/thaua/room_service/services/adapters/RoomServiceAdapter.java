package com.br.thaua.room_service.services.adapters;

import com.br.thaua.room_service.core.repository.EquipmentRepositoryPort;
import com.br.thaua.room_service.core.repository.FeedBackRepositoryPort;
import com.br.thaua.room_service.core.repository.RoomEquipmentRepositoryPort;
import com.br.thaua.room_service.domain.*;
import com.br.thaua.room_service.messaging.mappers.RoomEventMapper;
import com.br.thaua.room_service.core.messaging.publishers.RoomEventPublisherPort;
import com.br.thaua.room_service.core.repository.RoomRepositoryPort;
import com.br.thaua.room_service.core.services.RoomServicePort;
import com.br.thaua.room_service.persistence.mappers.EquipmentMapper;
import com.br.thaua.room_service.persistence.models.RoomEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceAdapter implements RoomServicePort {
    private final RoomEventPublisherPort roomEventPublisherPort;
    private final RoomRepositoryPort roomRepositoryPort;
    private final FeedBackRepositoryPort feedBackRepositoryPort;
    private final EquipmentRepositoryPort equipmentRepositoryPort;
    private final RoomEquipmentRepositoryPort roomEquipmentRepositoryPort;
    private final RoomEventMapper roomEventMapper;

    @Override
    public Room addNewRoom(Room room) {
        room.setStat(RoomStat.AVAILABLE);
        Room saved = roomRepositoryPort.save(room);

        roomEventPublisherPort.sendToRoomExchange(roomEventMapper.map(EventType.ROOM_CREATED, saved));
        return saved;
    }

    @Override
    public Room likeRoom(Long authId, Long roomId) {
        Room liked = roomRepositoryPort.findById(roomId);
        roomEventPublisherPort.sendToRoomExchange(roomEventMapper.map(EventType.ROOM_LIKED, liked, authId));
        return liked;
    }

    @Override
    public Equipment addNewEquipment(Equipment equipment) {
        return equipmentRepositoryPort.save(equipment);
    }

    @Override
    public Room addEquipmentsInRoom(Long equipmentsId, Long roomId, Integer quantity) {
        return roomEquipmentRepositoryPort.updated(equipmentsId, roomId, quantity);
    }

    @Override
    public Room updateRoomById(Long id, Room room) {
        Room roomUpdate = roomRepositoryPort.findById(id);

        roomUpdate.setCapacity(room.getCapacity());
        roomUpdate.setRoomNumber(roomUpdate.getRoomNumber());
        roomUpdate.setStat(room.getStat());
        Room updated = roomRepositoryPort.update(roomUpdate);

        roomEventPublisherPort.sendToRoomExchange(roomEventMapper.map(EventType.ROOM_UPDATED, room));
        return updated;
    }

    @Override
    public Room fetchRoomById(Long id) {
        return roomRepositoryPort.findById(id);
    }

    @Override
    public FeedBack addFeedBackForRoom(FeedBack feedBack, Long roomId, Long authId, String authEmail) {
        feedBack.setAuthorId(authId);
        feedBack.setAuthor(authEmail);

        roomEventPublisherPort.sendToRoomExchange(roomEventMapper.map(EventType.ROOM_FEEDBACK, authId));
        return feedBackRepositoryPort.save(feedBack, roomId);
    }

    @Override
    public List<FeedBack> listFeedBackOfRoom(Long roomId, FeedBackType feedBackType, String author, Long authorId) {
        if(feedBackType != null && author != null) {
            return feedBackRepositoryPort.listFeedBacksByFeedBackTypeAndAuthor(roomId, feedBackType, author);
        }

        if(feedBackType != null && authorId != null) {
            return feedBackRepositoryPort.listFeedBacksByFeedBackTypeAndAuthorId(roomId, feedBackType, authorId);
        }

        if(feedBackType != null) {
            return feedBackRepositoryPort.listFeedBacksByFeedBackType(roomId, feedBackType);
        }

        if(author != null) {
            return feedBackRepositoryPort.listFeedBacksByAuthor(roomId, author);
        }

        if(authorId != null) {
            return feedBackRepositoryPort.listFeedBacksByAuthorId(roomId, authorId);
        }

        return feedBackRepositoryPort.findAll();
    }

    @Override
    public void deleteRoomById(Long id) {
        Room deleted = roomRepositoryPort.findById(id);

        if(deleted == null) {
            return;
        }

        roomEventPublisherPort.sendToRoomExchange(roomEventMapper.map(EventType.ROOM_DELETED, deleted));
        roomRepositoryPort.deleteById(id);
    }

    @Override
    public Equipment fetchEquipmentById(Long equipmentId) {
        return equipmentRepositoryPort.findById(equipmentId);
    }

    @Override
    public List<Room> listRoom(RoomStat stat, FeedBackType feedBackType, List<String> equipments) {
        if(stat != null && feedBackType != null && equipments != null) {
            return roomRepositoryPort.findAllRoomEquipment(stat, equipments, feedBackType);
        }

        if(stat != null && feedBackType != null) {
            return roomRepositoryPort.findAllRoomEquipment(stat, feedBackType);
        }

        if(stat != null && equipments != null) {
            return roomRepositoryPort.findAllRoomEquipment(stat, equipments);
        }

        if(equipments != null && feedBackType != null) {
            return roomRepositoryPort.findAllRoomEquipment(equipments, feedBackType);
        }

        if(stat != null) {
            return roomRepositoryPort.findAllRoomEquipment(stat);
        }

        if(feedBackType != null) {
            return roomRepositoryPort.findAllRoomEquipment(feedBackType);
        }

        if(equipments != null) {
            return roomRepositoryPort.findAllRoomEquipment(equipments);
        }
        return roomRepositoryPort.findAll();
    }

    @Override
    public List<Equipment> listEquipments() {
        return equipmentRepositoryPort.findAll();
    }

    @Override
    public FeedBack fetchFeedBackById(Long roomId, Long feedBackId) {
        return feedBackRepositoryPort.findByIdAndRoomId(roomId, feedBackId);
    }

    @Override
    public FeedBack updateFeedBackById(Long feedBackId, FeedBack feedBack, Long authId, String authEmail) {
        FeedBack updated = feedBackRepositoryPort.findById(feedBackId);

        if(!updated.getAuthorId().equals(authId)) {
            return null;
        }

        updated.setAuthorId(authId);
        updated.setAuthor(authEmail);
        updated.setFeedBack(feedBack.getFeedBack());
        updated.setFeedBackType(feedBack.getFeedBackType());
        return feedBackRepositoryPort.update(updated);
    }

    @Override
    public void deleteFeedBackById(Long feedBackId, Long authId) {
        feedBackRepositoryPort.deleteById(feedBackId, authId);
    }

    @Override
    public void updateRoomStat(Long roomId, RoomStat roomStat) {
        roomRepositoryPort.updateRoomStat(roomId, roomStat);
    }
}
