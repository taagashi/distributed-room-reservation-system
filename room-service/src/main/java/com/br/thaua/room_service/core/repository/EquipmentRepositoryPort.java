package com.br.thaua.room_service.core.repository;

import com.br.thaua.room_service.domain.Equipment;

import java.util.List;

public interface EquipmentRepositoryPort {
    Equipment save(Equipment equipment);
    Equipment update(Equipment equipment);
    Equipment findById(Long id);
    List<Equipment> findAll();
    List<Equipment> findAllById(List<Long> ids);
    void deleteById(Long id);
}
