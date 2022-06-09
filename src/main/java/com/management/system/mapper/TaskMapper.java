package com.management.system.mapper;

import org.mapstruct.Mapper;

import com.management.system.dto.TaskDTO;
import com.management.system.model.Task;

/**
 * Mapper for the entity {@link Task} and its DTO {@link TaskDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {}
