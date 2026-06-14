package com.vm.guesthouse.service.mapper.checkIn;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
	@Mapping(target = "fullName", source = "primaryGuest.fullName")
    @Mapping(target = "mobile", source = "primaryGuest.mobile")
    @Mapping(target = "city", source = "primaryGuest.city")
    @Mapping(target = "state", source = "primaryGuest.state")
    @Mapping(target = "address", source = "primaryGuest.address")
    @Mapping(target = "gender", source = "primaryGuest.gender")
    @Mapping(target = "idProofNo", source = "primaryGuest.idProofNo")
	CheckInDto toDto(CheckIn entity);

	CheckIn toEntity(CheckInDto dto);

    List<CheckInDto> toDtoList(List<CheckIn> entities);

    List<CheckIn> toEntityList(List<CheckInDto> dtos);
}
