package com.br.thaua.employee_service.persistence.repositories;

import com.br.thaua.employee_service.persistence.models.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findByEmail(String email);
    void deleteByEmail(String email);
}
