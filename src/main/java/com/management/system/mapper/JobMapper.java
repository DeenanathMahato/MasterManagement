package com.management.system.mapper;

import org.mapstruct.Mapper;

import com.management.system.dto.JobDTO;
import com.management.system.model.Job;

/**
 * Mapper for the entity {@link Job} and its DTO {@link JobDTO}.
 */
@Mapper(componentModel = "spring")
public interface JobMapper extends EntityMapper<JobDTO, Job> {

}
