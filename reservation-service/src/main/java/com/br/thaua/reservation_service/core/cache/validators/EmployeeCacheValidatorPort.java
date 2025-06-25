package com.br.thaua.reservation_service.core.cache.validators;

import com.br.thaua.reservation_service.domain.Employee;

public interface EmployeeCacheValidatorPort {
    void validateEmployeeCache(Long employeeId, Employee employee);
}
