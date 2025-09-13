package com.br.thaua.employee_service.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favRoom_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavRoomEntity {
    @Id
    private Long id;
    private Integer roomNumber;
    private String state;

    @ManyToOne
    private EmployeeEntity employee;
}