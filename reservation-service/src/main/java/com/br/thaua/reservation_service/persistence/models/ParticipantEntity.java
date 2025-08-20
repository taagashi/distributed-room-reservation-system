package com.br.thaua.reservation_service.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "participant_tb")
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantEntity {
    private Long authId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @ManyToOne
    private TypeParticipantEntity typeParticipant;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private ReservationEntity reservation;

    private boolean cheackin = false;
}
