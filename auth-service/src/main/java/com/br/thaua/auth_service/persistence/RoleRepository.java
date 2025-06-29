package com.br.thaua.auth_service.persistence;

import com.br.thaua.auth_service.persistence.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
