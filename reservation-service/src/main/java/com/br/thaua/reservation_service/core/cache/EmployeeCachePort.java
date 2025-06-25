package com.br.thaua.reservation_service.core.cache;

import com.br.thaua.reservation_service.domain.Employee;

public interface EmployeeCachePort {
    Employee getCacheEmployee(String key);
    void putCacheEmployee(Employee employee);
    void updateCacheEmployee(Employee employee);
    void evictEmployee(Long employeeId);
}
