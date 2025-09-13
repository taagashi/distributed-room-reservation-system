package com.br.thaua.employee_service.persistence.adapters;

import com.br.thaua.employee_service.core.repository.FavRoomRepositoryPort;
import com.br.thaua.employee_service.domain.FavRoom;
import com.br.thaua.employee_service.persistence.mappers.FavRoomMapper;
import com.br.thaua.employee_service.persistence.models.FavRoomEntity;
import com.br.thaua.employee_service.persistence.repositories.FavRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FavRoomRepositoryAdapter implements FavRoomRepositoryPort {
    private final FavRoomRepository favRoomRepository;
    private final FavRoomMapper mapper;

    @Override
    public void save(FavRoom favRoom) {
        FavRoomEntity saved = mapper.map(favRoom);
        favRoomRepository.save(saved);
    }

    @Override
    public void deleteById(Long roomId) {
        favRoomRepository.deleteById(roomId);
    }
}
