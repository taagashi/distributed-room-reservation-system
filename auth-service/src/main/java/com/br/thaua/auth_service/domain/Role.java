package com.br.thaua.auth_service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    EMPLOYEE(1L),
    ADMIN(2L),
    ROOT(3L);

    private final Long id;
}
