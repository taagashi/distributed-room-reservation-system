package com.br.thaua.employee_service.persistence.repositories;

import com.br.thaua.employee_service.persistence.models.FavRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavRoomRepository extends JpaRepository<FavRoomEntity, Long> {
}
