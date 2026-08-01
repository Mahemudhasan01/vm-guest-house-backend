package com.vm.guesthouse.service.mapper.extraPerson;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.vm.guesthouse.dto.checkin.ExtraPersonDto;
import com.vm.guesthouse.entity.ExtraPerson;

@Mapper(
	    componentModel = "spring",
	    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
	    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
	)
public interface ExtraPersonMapper {
	
	ExtraPersonDto toDto(ExtraPerson entity);

	@Mapping(target = "checkIn", ignore = true)
	ExtraPerson toEntity(ExtraPersonDto dto);

//    List<ExtraPersonDto> toDtoList(List<ExtraPerson> entities);

//    List<ExtraPerson> toEntityList(List<ExtraPersonDto> dtos);

	@InheritConfiguration(name = "toEntity")
	ExtraPerson updateToEntity(ExtraPersonDto dto, @MappingTarget  ExtraPerson entity);
	
	@InheritInverseConfiguration(name = "updateToEntity")
    ExtraPersonDto updateToDto(ExtraPerson entity, @MappingTarget ExtraPersonDto dto);
	
}

