package com.br.thaua.reservation_service.core.restClient;

import com.br.thaua.reservation_service.domain.Auth;
import com.br.thaua.reservation_service.domain.Room;

public interface RestClientPort {
    Auth fetchAuthById(String email);
    Room fetchRoomById(Long roomId);
}
