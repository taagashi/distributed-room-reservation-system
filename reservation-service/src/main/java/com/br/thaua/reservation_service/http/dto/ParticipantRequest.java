package com.br.thaua.reservation_service.http.dto;

import com.br.thaua.reservation_service.domain.TypeParticipant;

public record ParticipantRequest(String email, TypeParticipant typeParticipant) {
}
