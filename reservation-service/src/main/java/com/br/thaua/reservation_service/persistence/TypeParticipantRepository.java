package com.br.thaua.reservation_service.persistence;

import com.br.thaua.reservation_service.persistence.models.TypeParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeParticipantRepository extends JpaRepository<TypeParticipantEntity, Long> {
}
