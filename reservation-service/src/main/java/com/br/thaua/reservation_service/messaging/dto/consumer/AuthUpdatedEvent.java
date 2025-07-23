package com.br.thaua.reservation_service.messaging.dto;

public record AuthUpdatedEvent(Long id, String email, String oldEmail) {}
