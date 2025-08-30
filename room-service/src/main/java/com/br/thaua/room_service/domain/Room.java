package com.br.thaua.room_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Room {
    private Long roomId;
    private Integer roomNumber;
    private Integer capacity;
    private RoomStat stat;
    private List<Equipment> equipments;
    private List<FeedBack> feedBacks;
}
