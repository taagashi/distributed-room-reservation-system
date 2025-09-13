package com.br.thaua.employee_service.http.dto;

import com.br.thaua.employee_service.domain.EmployeeState;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EmployeeResponse(
        Long id,
        String email,
        BigDecimal salary,
        Integer score,
        LocalDateTime dateOfHiring,
        EmployeeState employeeState
) {}