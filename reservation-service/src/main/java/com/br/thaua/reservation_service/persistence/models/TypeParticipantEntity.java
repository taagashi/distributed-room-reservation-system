package com.br.thaua.reservation_service.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "TYPEPARTICIPANT_TB")
@AllArgsConstructor
@NoArgsConstructor
public class TypeParticipantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

//    @OneToMany(mappedBy = "typeParticipantEntity")
//    private List<ParticipantEntity> participantEntity;
}
