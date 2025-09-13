package com.br.thaua.employee_service.persistence.repositories;

import com.br.thaua.employee_service.persistence.models.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findByEmail(String email);
    void deleteByEmail(String email);

    @Transactional
    @Modifying
    @Query("""
            UPDATE EmployeeEntity e SET e.score = ?2 + e.score
            WHERE e.id = ?1
            """)
    void increaseEmployeeScore(Long employeeId, int score);

    @Transactional
    @Modifying
    @Query("""
            UPDATE EmployeeEntity e SET e.score = e.score - ?2
            WHERE e.id = ?1
            AND e.score >= 0
            AND e.score >= ?2
            """)
    void deCreaseEmployeeScore(Long employeeId, int score);

    @Transactional
    @Modifying
    @Query("""
            UPDATE EmployeeEntity e SET e.email = ?2
            WHERE e.id = ?1
            """)
    void update(Long employeeId, String email);
}
