package com.br.thaua.employee_service.core.repository;

import com.br.thaua.employee_service.domain.FavRoom;

public interface FavRoomRepositoryPort {
    void save(FavRoom favRoom);
    void deleteById(Long roomId);
}
