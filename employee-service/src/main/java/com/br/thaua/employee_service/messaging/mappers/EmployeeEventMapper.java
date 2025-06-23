package com.br.thaua.employee_service.messaging.mappers;

import com.br.thaua.employee_service.domain.Employee;
import com.br.thaua.employee_service.messaging.dto.EmployeeEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeEventMapper {
    EmployeeEvent map(Employee employee);
}
