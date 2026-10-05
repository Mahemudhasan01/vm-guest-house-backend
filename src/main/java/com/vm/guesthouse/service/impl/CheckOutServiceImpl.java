package com.vm.guesthouse.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vm.guesthouse.constants.MessageConstants;
import com.vm.guesthouse.dto.CheckOutDTO;
import com.vm.guesthouse.entity.CheckIn;
import com.vm.guesthouse.entity.Room;
import com.vm.guesthouse.enums.Enums;
import com.vm.guesthouse.exceptions.CustomExceptions;
import com.vm.guesthouse.repository.checkIn.CheckInRepository;
import com.vm.guesthouse.repository.room.RoomRepository;
import com.vm.guesthouse.service.CheckOutService;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class CheckOutServiceImpl implements CheckOutService{
	final private CheckInRepository checkInRepository;
	final private RoomRepository roomRepository;
	
	public CheckOutServiceImpl(CheckInRepository checkInRepository, RoomRepository roomRepository) {
		this.checkInRepository = checkInRepository;
		this.roomRepository = roomRepository; 
	}
	
	@Override
	public CheckOutDTO proceedCheckOut(CheckOutDTO dto) {
		CheckIn checkIn = checkInRepository.findById( dto.getId() )
				.orElseThrow(() -> new CustomExceptions( MessageConstants.CHECKIN_NOT_FOUND, HttpStatus.BAD_REQUEST.value() ));
		
		Room room = roomRepository.findById(checkIn.getRoom().getId() )
				.orElseThrow(() -> new CustomExceptions( MessageConstants.ROOM_NOT_FOUND, HttpStatus.BAD_REQUEST.value() ));
		
		setCheckOutDetails(checkIn, dto);
		changeRoomStatus(room, dto);
		
		return null;
	}

	private void setCheckOutDetails(CheckIn checkIn, CheckOutDTO dto) {
		checkIn.setStayStatus(Enums.StayStatus.CHECKED_OUT);
		checkIn.setCheckOutDate( dto.getCheckOutDate() );
		checkIn.setCheckOutTime( dto.getCheckOutTime() );
		checkIn.setRemarks( dto.getRemarks() );
		
		checkInRepository.save(checkIn);
	}
	
	private void changeRoomStatus(Room room, CheckOutDTO dto) {
		room.setStatus( Enums.RoomStatus.CLEANING );
		
		roomRepository.save(room);
	}
	
	
}
