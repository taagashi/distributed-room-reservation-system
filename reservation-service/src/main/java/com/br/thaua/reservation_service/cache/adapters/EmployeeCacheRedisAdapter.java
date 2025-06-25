package com.br.thaua.reservation_service.cache.adapters;

import com.br.thaua.reservation_service.core.cache.EmployeeCachePort;
import com.br.thaua.reservation_service.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmployeeCacheRedisAdapter implements EmployeeCachePort {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Employee getCacheEmployee(String key) {
        return (Employee) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void putCacheEmployee(Employee employee) {
        redisTemplate.opsForValue().set(employeeKey(employee.getId()), employee, Duration.ofMinutes(30));
    }

    @Override
    public void updateCacheEmployee(Employee employee) {
        redisTemplate.opsForValue().set(employeeKey(employee.getId()), employee, Duration.ofMinutes(30));
    }

    @Override
    public void evictEmployee(Long employeeId) {
        redisTemplate.delete(employeeKey(employeeId));
    }

    private String employeeKey(Long employeeId) {
        return "employee:" + employeeId;
    }
}
