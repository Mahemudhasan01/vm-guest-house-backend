package com.vm.guesthouse.service.checkin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vm.guesthouse.dto.PageableResponse;
import com.vm.guesthouse.dto.PaginationSortingFilterSearchDTO;
import com.vm.guesthouse.dto.checkin.CheckInDto;
import com.vm.guesthouse.entity.CheckIn;
import com.vm.guesthouse.entity.Room;
import com.vm.guesthouse.repository.room.RoomRepository;
import com.vm.guesthouse.service.checkin.CheckInService;
import com.vm.guesthouse.service.mapper.checkIn.CheckInMapper;

@Service
public class CheckInServiceImpl implements CheckInService{
	@Autowired
	private CheckInMapper checkInMapper;
	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public CheckInDto save(CheckInDto dto) {
		//Validate Room
//		Room room = roomRepository.findById(dto.getRoomId()).orElseThrow(() -> new Exception("Room not found"));
		return null;
	}

	@Override
	public CheckInDto update(CheckInDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckInDto get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageableResponse<CheckInDto> getAll(PaginationSortingFilterSearchDTO paginationSortingFilterSearchDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
