package com.vm.guesthouse.service.mapper.checkInGuestList;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.vm.guesthouse.dto.checkin.ExtraPersonDto;
import com.vm.guesthouse.entity.ExtraPerson;

@Mapper(
	    componentModel = "spring",
	    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
	    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
	)
public interface CheckInGuestListMapper {
	
	ExtraPersonDto toDto(ExtraPerson entity);

	ExtraPerson toEntity(ExtraPersonDto dto);

    List<ExtraPersonDto> toDtoList(List<ExtraPerson> entities);

    List<ExtraPerson> toEntityList(List<ExtraPersonDto> dtos);
}
