package com.br.thaua.auth_service.core.services;

import com.br.thaua.auth_service.domain.Auth;

public interface AuthServicePort {
    String singIn(Auth auth);
    String login(Auth auth);
    String updateAuthById(Long id, Auth auth);
    Auth fetchAuthById(Long id);
    void deleteAuthById(Long id);
}
