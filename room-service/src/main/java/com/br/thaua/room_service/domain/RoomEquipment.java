package com.br.thaua.room_service.domain;

import com.br.thaua.room_service.persistence.models.EquipmentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomEquipment {
    private Long roomId;
    private Integer roomNumber;
    private Integer capacity;
    private RoomStat stat;
    private List<Equipment> equipments;
    private List<FeedBack> feedBacks;
}
