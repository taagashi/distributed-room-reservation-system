package com.br.thaua.room_service.persistence.adapters;

import com.br.thaua.room_service.core.repository.EquipmentRepositoryPort;
import com.br.thaua.room_service.domain.Equipment;
import com.br.thaua.room_service.persistence.mappers.EquipmentMapper;
import com.br.thaua.room_service.persistence.models.EquipmentEntity;
import com.br.thaua.room_service.persistence.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EquipmentRepositoryAdapter implements EquipmentRepositoryPort {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper mapper;

    @Override
    public Equipment save(Equipment equipment) {
        EquipmentEntity saved = equipmentRepository.save(mapper.map(equipment));
        return mapper.map(saved);
    }

    @Override
    public Equipment update(Equipment equipment) {
        EquipmentEntity updated = equipmentRepository.save(mapper.map(equipment));
        return mapper.map(updated);
    }

    @Override
    public Equipment findById(Long id) {
        return mapper.map(equipmentRepository.findById(id).get());
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Equipment> findAllById(List<Long> ids) {
        return equipmentRepository.findAllById(ids)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }
}
