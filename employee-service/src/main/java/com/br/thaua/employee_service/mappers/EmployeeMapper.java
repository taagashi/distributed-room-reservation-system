package com.br.thaua.employee_service.mappers;

import com.br.thaua.employee_service.controllers.dto.EmployeeRequest;
import com.br.thaua.employee_service.controllers.dto.EmployeeResponse;
import com.br.thaua.employee_service.models.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeEntity map(EmployeeRequest employeeRequest);
    EmployeeResponse map(EmployeeEntity employeeEntity);
}
