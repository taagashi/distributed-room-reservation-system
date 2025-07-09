package com.br.thaua.reservation_service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TypeParticipant {
    GUEST(1L),
    HOST(2L);

    private final Long id;
}
