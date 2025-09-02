package com.br.thaua.room_service.persistence.repository;

import com.br.thaua.room_service.persistence.models.RoomEquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomEquipmentRepository extends JpaRepository<RoomEquipmentEntity, Long> {
}
