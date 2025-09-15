package com.br.thaua.reservation_service.messaging.dto.consumer;

public record AuthUpdatedEventConsumer(Long authId, String email, String oldEmail) {}
