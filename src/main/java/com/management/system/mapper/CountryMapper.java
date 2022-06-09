package com.management.system.mapper;

import org.mapstruct.Mapper;

import com.management.system.dto.CountryDTO;
import com.management.system.model.Country;

/**
 * Mapper for the entity {@link Country} and its DTO {@link CountryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {
}
