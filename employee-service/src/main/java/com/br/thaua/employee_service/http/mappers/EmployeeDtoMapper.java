package com.br.thaua.employee_service.http.mappers;

import com.br.thaua.employee_service.domain.Employee;
import com.br.thaua.employee_service.http.dto.EmployeeRequest;
import com.br.thaua.employee_service.http.dto.EmployeeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeDtoMapper {
    Employee map(EmployeeRequest employeeRequest);
    EmployeeResponse map(Employee employee);
}
