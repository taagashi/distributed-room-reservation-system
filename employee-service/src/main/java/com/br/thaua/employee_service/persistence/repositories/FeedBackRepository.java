package com.br.thaua.employee_service.persistence.repositories;

import com.br.thaua.employee_service.domain.FeedBack;
import com.br.thaua.employee_service.persistence.models.FeedBackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<FeedBackEntity, Long> {
    @Query("""
            SELECT f FROM FeedBackEntity f
            WHERE f.author.id = ?1
            """)
    List<FeedBackEntity> listFeedBacksOfEmployee(Long employeeId);

    @Query("""
            SELECT f FROM FeedBackEntity f
            WHERE f.receiver.id = ?1
            """)
    List<FeedBackEntity> listFeedBacksReceivedOfEmployee(Long employeeId);
}
