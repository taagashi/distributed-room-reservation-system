package com.br.thaua.auth_service.security.service;

import com.br.thaua.auth_service.core.persistence.AuthRepositoryPort;
import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.security.details.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    private final AuthRepositoryPort authRepositoryPort;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Auth auth = authRepositoryPort.findByEmail(username);
        return new AuthDetails(auth.getId(), auth.getName(), auth.getEmail(), auth.getPassword(), auth.getRoles());
    }
}
