package com.br.thaua.reservation_service.messaging.dto.consumer;

public record AuthUpdatedEventConsumer(Long id, String email, String oldEmail) {}
