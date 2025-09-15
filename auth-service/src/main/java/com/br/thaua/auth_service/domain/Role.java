package com.br.thaua.auth_service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum Role {
    EMPLOYEE(1L),
    ADMIN(2L),
    ROOT(3L);

    private final Long id;

    private static final Map<Long, Role> FROM_ID = new HashMap<>();

    static {
        for (Role role : values()) {
            FROM_ID.put(role.getId(), role);
        }
    }

    public static Role fromId(Long id) {
        return FROM_ID.get(id);
    }
}
