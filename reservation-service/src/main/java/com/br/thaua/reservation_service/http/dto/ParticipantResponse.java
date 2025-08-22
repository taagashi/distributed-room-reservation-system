package com.br.thaua.reservation_service.http.dto;

import com.br.thaua.reservation_service.domain.TypeParticipant;

public record ParticipantResponse(Long id, String email, TypeParticipant typeParticipant, Long reservationId, boolean cheackin) {}
