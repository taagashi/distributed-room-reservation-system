package com.br.thaua.auth_service.services.adapters;

import com.br.thaua.auth_service.core.persistence.AuthRepositoryPort;
import com.br.thaua.auth_service.core.services.AuthServicePort;
import com.br.thaua.auth_service.core.token.TokenManager;
import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.domain.Role;
import com.br.thaua.auth_service.security.details.AuthDetails;
import com.br.thaua.auth_service.security.mappers.AuthSecurityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceAdapter implements AuthServicePort {
    private final AuthRepositoryPort authRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthSecurityMapper authSecurityMapper;
    private final TokenManager tokenManager;

    @Override
    public String singIn(Auth auth) {
        auth.setPassword(passwordEncoder.encode(auth.getPassword()));
        auth.setRoles(List.of(Role.EMPLOYEE));
        Auth sing = authRepositoryPort.save(auth);
        return tokenManager.generateToken(sing);
    }

    @Override
    public String login(Auth auth) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword());
        UsernamePasswordAuthenticationToken authenticated = (UsernamePasswordAuthenticationToken) authenticationManager.authenticate(authentication);
        Auth login = authSecurityMapper.map((AuthDetails) authenticated.getPrincipal());
        return tokenManager.generateToken(login);
    }

    @Override
    public String updateAuthById(Long id, Auth auth) {
        Auth updated = authRepositoryPort.findById(id);

        updated.setName(auth.getName());
        updated.setEmail(auth.getEmail());
        updated.setPassword(passwordEncoder.encode(auth.getPassword()));
        authRepositoryPort.update(updated);
        return tokenManager.generateToken(updated);
    }

    @Override
    public Auth fetchAuthById(Long id) {
        return authRepositoryPort.findById(id);
    }

    @Override
    public void deleteAuthById(Long id) {
        Auth deleted = authRepositoryPort.findById(id);
        deleted.setRoles(null);
        authRepositoryPort.update(deleted);
        authRepositoryPort.deleteById(id);
    }
}
