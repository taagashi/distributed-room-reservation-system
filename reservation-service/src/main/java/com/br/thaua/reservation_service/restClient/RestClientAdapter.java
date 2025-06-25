package com.br.thaua.reservation_service.restClient;

import com.br.thaua.reservation_service.core.restClient.RestClientPort;
import com.br.thaua.reservation_service.domain.Employee;
import com.br.thaua.reservation_service.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestClientAdapter implements RestClientPort {
    private final RestTemplate restTemplate;

    @Override
    public Employee fetchEmployeeById(Long employeeId) {
        return restTemplate.getForObject("lb://employee-service/api/v1/employee?id=" + employeeId, Employee.class);
    }

    @Override
    public Room fetchRoomById(Long roomId) {
        return restTemplate.getForObject("lb://room-service/api/v1/room/" + roomId, Room.class);
    }
}
