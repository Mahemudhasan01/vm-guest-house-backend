package com.vm.guesthouse.service.mapper.checkIn;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.vm.guesthouse.dto.checkin.CheckInDto;
import com.vm.guesthouse.dto.guest.GuestDto;
import com.vm.guesthouse.entity.CheckIn;
import com.vm.guesthouse.entity.Guest;

@Mapper(
	    componentModel = "spring",
	    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
	    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
	)
public interface CheckInMapper {
	
	CheckInDto toDto(CheckIn entity);

	CheckIn toEntity(CheckInDto dto);

    List<CheckInDto> toDtoList(List<CheckIn> entities);

    List<CheckIn> toEntityList(List<CheckInDto> dtos);
}
