package com.management.system.mapper;

import org.mapstruct.Mapper;

import com.management.system.dto.LocationDTO;
import com.management.system.model.Location;

/**
 * Mapper for the entity {@link Location} and its DTO {@link LocationDTO}.
 */
@Mapper(componentModel = "spring")
public interface LocationMapper extends EntityMapper<LocationDTO, Location> {
}
