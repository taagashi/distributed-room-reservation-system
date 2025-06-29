package com.br.thaua.auth_service.persistence;

import com.br.thaua.auth_service.persistence.models.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    AuthEntity findByEmail(String email);
}
