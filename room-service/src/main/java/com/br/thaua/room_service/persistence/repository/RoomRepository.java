package com.br.thaua.room_service.persistence.repository;

import com.br.thaua.room_service.persistence.models.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
