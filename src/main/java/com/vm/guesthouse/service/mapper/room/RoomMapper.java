package com.vm.guesthouse.service.mapper.room;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.vm.guesthouse.dto.checkin.CheckInDto;
import com.vm.guesthouse.dto.room.RoomDto;
import com.vm.guesthouse.entity.CheckIn;
import com.vm.guesthouse.entity.Room;

@Mapper(
	    componentModel = "spring",
	    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
	    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
	)
public interface RoomMapper {
	
	RoomDto toDto(Room entity);

	Room toEntity(RoomDto dto);

    List<RoomDto> toDtoList(List<Room> entities);

    List<Room> toEntityList(List<RoomDto> dtos);
}