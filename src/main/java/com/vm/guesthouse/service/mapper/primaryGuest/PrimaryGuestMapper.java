package com.vm.guesthouse.service.mapper.primaryGuest;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.vm.guesthouse.dto.guest.PrimaryGuestDto;
import com.vm.guesthouse.entity.PrimaryGuest;

@Mapper(
	    componentModel = "spring",
	    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
	    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
	)
public interface PrimaryGuestMapper {
	
	PrimaryGuestDto toDto(PrimaryGuest entity);
	
	PrimaryGuest toEntity(PrimaryGuestDto dto);
	
    @InheritConfiguration(name = "toEntity")
    PrimaryGuest updateToEntity(PrimaryGuestDto dto, @MappingTarget PrimaryGuest entity);

    @InheritInverseConfiguration(name = "updateToEntity")
    PrimaryGuestDto updateToDTO(PrimaryGuest entity, @MappingTarget PrimaryGuestDto dto);
}
