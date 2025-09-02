package com.br.thaua.room_service.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "equipment_tb")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EquipmentEntity extends AbstractEntity{
    private String name;
}
