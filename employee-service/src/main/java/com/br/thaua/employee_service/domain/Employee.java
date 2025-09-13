package com.br.thaua.employee_service.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Employee {
    private Long id;
    private String email;
    private LocalDateTime dateOfHiring;
    private BigDecimal salary;
    private Integer score;
    private EmployeeState employeeState;

    public void verifyScore() {
        if(this.score <= 30) {
            this.changeStateToUncommitted();
            return;
        }

        if(this.score <= 80) {
            this.changeStateToMinimallyCommitted();
            return;
        }

        if(this.score <= 190 ) {
            this.changeStateToCommitted();
            return;
        }

        this.changeStateToFullyCommitted();
    }

    public void changeStateToUncommitted() {
        this.employeeState = EmployeeState.UNCOMMITTED;
    }

    public void changeStateToMinimallyCommitted() {
        this.employeeState = EmployeeState.MINIMALLY_COMMITTED;
    }

    public void changeStateToCommitted() {
        this.employeeState = EmployeeState.COMMITTED;
    }

    public void changeStateToFullyCommitted() {
        this.employeeState = EmployeeState.FULLY_COMMITTED;
    }
}
