package com.br.thaua.reservation_service.cache.adapters.validators;

import com.br.thaua.reservation_service.core.cache.EmployeeCachePort;
import com.br.thaua.reservation_service.core.cache.validators.EmployeeCacheValidatorPort;
import com.br.thaua.reservation_service.core.restClient.RestClientPort;
import com.br.thaua.reservation_service.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeCacheRedisValidatorPortAdapter implements EmployeeCacheValidatorPort {
    private final RestClientPort restClientPort;
    private final EmployeeCachePort employeeCachePort;

    @Override
    public void validateEmployeeCache(Long employeeId, Employee employee) {
        if(employee == null) {
            employee = restClientPort.fetchEmployeeById(employeeId);
        }

        if(employee != null) {
            employeeCachePort.putCacheEmployee(employee);
            return;
        }
        throw new RuntimeException("Employee not found");
    }
}
