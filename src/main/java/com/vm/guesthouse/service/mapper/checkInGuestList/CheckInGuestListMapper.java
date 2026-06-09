package com.vm.guesthouse.service.mapper.checkInGuestList;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.vm.guesthouse.dto.checkin.CheckInGuestListDto;
import com.vm.guesthouse.entity.CheckInGuestList;

@Mapper(
	    componentModel = "spring",
	    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
	    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
	)
public interface CheckInGuestListMapper {
	
	CheckInGuestListDto toDto(CheckInGuestList entity);

	CheckInGuestList toEntity(CheckInGuestListDto dto);

    List<CheckInGuestListDto> toDtoList(List<CheckInGuestList> entities);

    List<CheckInGuestList> toEntityList(List<CheckInGuestListDto> dtos);
}
