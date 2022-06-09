package com.management.system.mapper;

import org.mapstruct.Mapper;

import com.management.system.dto.EmployeeDTO;
import com.management.system.model.Employee;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {
 
}
