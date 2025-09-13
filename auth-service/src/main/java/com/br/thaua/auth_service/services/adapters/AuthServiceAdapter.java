package com.br.thaua.auth_service.services.adapters;

import com.br.thaua.auth_service.core.messaging.publishers.AuthEventPublisherPort;
import com.br.thaua.auth_service.core.persistence.AuthRepositoryPort;
import com.br.thaua.auth_service.core.services.AuthServicePort;
import com.br.thaua.auth_service.core.token.TokenManagerPort;
import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.domain.EventType;
import com.br.thaua.auth_service.domain.Role;
import com.br.thaua.auth_service.messaging.dto.AuthEvent;
import com.br.thaua.auth_service.messaging.dto.AuthUpdatedEvent;
import com.br.thaua.auth_service.messaging.mappers.AuthEventMapper;
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
    private final TokenManagerPort tokenManagerPort;
    private final AuthEventPublisherPort authEventPublisherPort;
    private final AuthEventMapper authEventMapper;

    @Override
    public String singIn(Auth auth) {
        auth.setPassword(passwordEncoder.encode(auth.getPassword()));
        auth.setRoles(List.of(Role.EMPLOYEE));
        Auth sing = authRepositoryPort.save(auth);

        AuthEvent authEvent = authEventMapper.map(sing, EventType.AUTH_CREATED);
        authEventPublisherPort.sendToAuthExchange(authEvent);
        return tokenManagerPort.generateToken(sing);
    }

    @Override
    public String login(Auth auth) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword());
        UsernamePasswordAuthenticationToken authenticated = (UsernamePasswordAuthenticationToken) authenticationManager.authenticate(authentication);
        Auth login = authSecurityMapper.map((AuthDetails) authenticated.getPrincipal());
        return tokenManagerPort.generateToken(login);
    }

    @Override
    public String updateAuthById(Long id, Auth auth) {
        Auth updated = authRepositoryPort.findById(id);

        auth.setId(updated.getId());
        AuthUpdatedEvent updatedEvent = authEventMapper.map(auth, updated.getEmail(), EventType.AUTH_UPDATED);
        updated.setName(auth.getName());
        updated.setEmail(auth.getEmail());
        updated.setPassword(passwordEncoder.encode(auth.getPassword()));
        authRepositoryPort.update(updated);

        String token = tokenManagerPort.generateToken(updated);
        authEventPublisherPort.sendToAuthExchange(updatedEvent);
        return token;
    }

    @Override
    public Auth fetchAuthById(Long id) {
        return authRepositoryPort.findById(id);
    }

    @Override
    public void deleteAuthById(Long id) {
        Auth deleted = authRepositoryPort.findById(id);
        if(deleted == null) {
            return;
        }

        AuthEvent authEvent = authEventMapper.map(deleted, EventType.AUTH_DELETED);
        deleted.setRoles(null);
        authRepositoryPort.update(deleted);
        authRepositoryPort.deleteById(id);
        authEventPublisherPort.sendToAuthExchange(authEvent);
    }

    @Override
    public String createAccounts(Auth account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        Auth saved = authRepositoryPort.save(account);
        AuthEvent createdAccount = authEventMapper.map(saved, EventType.AUTH_CREATED);
        authEventPublisherPort.sendToAuthExchange(createdAccount);
        return tokenManagerPort.generateToken(saved);
    }

    @Override
    public Auth fetchAuthByEmail(String email) {
        return authRepositoryPort.findByEmail(email);
    }
}
