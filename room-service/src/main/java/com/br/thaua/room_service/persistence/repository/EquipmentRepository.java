package com.br.thaua.room_service.persistence.repository;

import com.br.thaua.room_service.persistence.models.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {
}
