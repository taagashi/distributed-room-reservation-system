package com.br.thaua.auth_service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN");

    private final String role;
}
