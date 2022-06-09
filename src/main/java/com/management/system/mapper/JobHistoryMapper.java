package com.management.system.mapper;

import org.mapstruct.Mapper;

import com.management.system.dto.JobHistoryDTO;
import com.management.system.model.JobHistory;

/**
 * Mapper for the entity {@link JobHistory} and its DTO {@link JobHistoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface JobHistoryMapper extends EntityMapper<JobHistoryDTO, JobHistory> {

}
