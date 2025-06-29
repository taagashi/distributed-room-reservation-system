package com.br.thaua.auth_service.persistence.adapters;

import com.br.thaua.auth_service.core.persistence.AuthRepositoryPort;
import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.persistence.AuthRepository;
import com.br.thaua.auth_service.persistence.mappers.AuthMapper;
import com.br.thaua.auth_service.persistence.models.AuthEntity;
import com.br.thaua.auth_service.persistence.models.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthRepositoryAdapter implements AuthRepositoryPort {
    private final AuthRepository authRepository;
    private final AuthMapper authMapper;

    @Override
    public Auth save(Auth auth) {
        AuthEntity saved = authMapper.map(auth);
        return authMapper.map(authRepository.save(saved));
    }

    @Override
    public Auth update(Auth auth) {
        AuthEntity updated = authMapper.map(auth);
        return authMapper.map(authRepository.save(updated));
    }

    @Override
    public Auth findById(Long id) {
        AuthEntity find = authRepository.findById(id).orElse(null);
        return authMapper.map(find);
    }

    @Override
    public Auth findByEmail(String email) {
        return authMapper.map(authRepository.findByEmail(email));
    }

    @Override
    public void deleteById(Long id) {
        authRepository.deleteById(id);
    }
}
