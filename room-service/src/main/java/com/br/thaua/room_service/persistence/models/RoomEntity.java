package com.br.thaua.room_service.persistence.models;

import com.br.thaua.room_service.domain.RoomStat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "room_tb")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RoomEntity extends AbstractEntity {
    private Integer capacity;
    private Integer roomNumber;

    @OneToMany(mappedBy = "room")
    private List<RoomEquipmentEntity> roomEquipments;

    @OneToMany(mappedBy = "room")
    private List<FeedBackEntity> feedBacks;

    @Enumerated(EnumType.STRING)
    private RoomStat stat;
}
