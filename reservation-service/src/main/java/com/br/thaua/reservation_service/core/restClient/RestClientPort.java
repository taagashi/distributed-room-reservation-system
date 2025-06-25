package com.br.thaua.reservation_service.core.restClient;

import com.br.thaua.reservation_service.domain.Employee;
import com.br.thaua.reservation_service.domain.Room;

public interface RestClientPort {
    Employee fetchEmployeeById(Long employeeId);
    Room fetchRoomById(Long roomId);
}
