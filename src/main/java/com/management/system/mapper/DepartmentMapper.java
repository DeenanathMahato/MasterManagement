package com.management.system.mapper;

import org.mapstruct.Mapper;

import com.management.system.dto.DepartmentDTO;
import com.management.system.model.Department;

/**
 * Mapper for the entity {@link Department} and its DTO {@link DepartmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department> {

}
