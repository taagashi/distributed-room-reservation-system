package com.br.thaua.auth_service.core.persistence;

import com.br.thaua.auth_service.domain.Auth;

public interface AuthRepositoryPort {
    Auth save(Auth auth);
    Auth update(Auth auth);
    Auth findById(Long id);
    Auth findByEmail(String email);
    void deleteById(Long id);
}
