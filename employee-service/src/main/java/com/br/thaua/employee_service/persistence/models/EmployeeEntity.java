package com.br.thaua.employee_service.persistence.models;

import com.br.thaua.employee_service.domain.EmployeeState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "employee_tb")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    private Long id;
    private String email;

    @CreationTimestamp
    private LocalDateTime dateOfHiring;
    private BigDecimal salary;
    private Integer score;

    @Enumerated(EnumType.STRING)
    private EmployeeState employeeState;
}
