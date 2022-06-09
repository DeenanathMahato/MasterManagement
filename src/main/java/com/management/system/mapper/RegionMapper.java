package com.management.system.mapper;

import org.mapstruct.Mapper;

import com.management.system.dto.RegionDTO;
import com.management.system.model.Region;

/**
 * Mapper for the entity {@link Region} and its DTO {@link RegionDTO}.
 */
@Mapper(componentModel = "spring")
public interface RegionMapper extends EntityMapper<RegionDTO, Region> {}
