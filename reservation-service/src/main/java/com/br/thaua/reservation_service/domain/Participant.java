package com.br.thaua.reservation_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Participant {
    private Long id;
    private Long authId;
    private String email;
    private TypeParticipant typeParticipant;
    private Reservation reservation;
    private boolean cheackin;
}
