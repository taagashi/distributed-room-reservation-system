package com.br.thaua.reservation_service.messaging.dto.publisher;

import com.br.thaua.reservation_service.domain.EventType;
import com.br.thaua.reservation_service.domain.TypeParticipant;

public record AuthEventPublisher (EventType eventType, TypeParticipant typeParticipant, Long authId){}
