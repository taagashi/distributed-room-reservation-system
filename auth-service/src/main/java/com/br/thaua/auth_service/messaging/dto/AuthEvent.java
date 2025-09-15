package com.br.thaua.auth_service.messaging.dto;

import com.br.thaua.auth_service.domain.EventType;

public record AuthEvent(EventType eventType, Long authId, String email) {}