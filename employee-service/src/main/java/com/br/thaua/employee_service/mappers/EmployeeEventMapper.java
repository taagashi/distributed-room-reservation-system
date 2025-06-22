package com.br.thaua.employee_service.mappers;

import com.br.thaua.employee_service.controllers.dto.EmployeeEvent;
import com.br.thaua.employee_service.models.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeEventMapper {
    EmployeeEvent map(EmployeeEntity employeeEntity);
}
