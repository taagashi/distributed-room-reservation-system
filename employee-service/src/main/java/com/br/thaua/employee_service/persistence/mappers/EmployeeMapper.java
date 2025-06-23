package com.br.thaua.employee_service.persistence.mappers;

import com.br.thaua.employee_service.domain.Employee;
import com.br.thaua.employee_service.persistence.models.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeEntity map(Employee employee);
    Employee map(EmployeeEntity employeeEntity);
}
