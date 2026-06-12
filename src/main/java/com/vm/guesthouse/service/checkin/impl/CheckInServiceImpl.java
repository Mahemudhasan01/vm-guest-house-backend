package com.vm.guesthouse.service.checkin.impl;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vm.guesthouse.dto.PageableResponse;
import com.vm.guesthouse.dto.PaginationSortingFilterSearchDTO;
import com.vm.guesthouse.dto.checkin.CheckInDto;
import com.vm.guesthouse.entity.Room;
import com.vm.guesthouse.enums.Enums.RoomStatus;
import com.vm.guesthouse.exceptions.CustomExceptions;
import com.vm.guesthouse.repository.checkIn.CheckInRepository;
import com.vm.guesthouse.repository.guest.GuestRepository;
import com.vm.guesthouse.repository.property.PropertyRepository;
import com.vm.guesthouse.repository.room.RoomRepository;
import com.vm.guesthouse.service.checkin.CheckInService;
import com.vm.guesthouse.service.mapper.checkIn.CheckInMapper;
import com.vm.guesthouse.service.mapper.checkInGuestList.CheckInGuestListMapper;
import com.vm.guesthouse.entity.CheckIn;
import com.vm.guesthouse.entity.CheckInGuestList;
import com.vm.guesthouse.entity.Guest;
import com.vm.guesthouse.entity.Property;

@Service
public class CheckInServiceImpl implements CheckInService{
	@Autowired
	private CheckInMapper checkInMapper;
	@Autowired
	private CheckInGuestListMapper checkInGuestListMapper;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private GuestRepository guestRepository;
	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private CheckInRepository checkInRepository;
	
	
	@Override
	public CheckInDto save(CheckInDto dto) {
		//Validate Property
		Property property = propertyRepository.findByIdAndIsActiveTrue(dto.getPropertyId() ).orElseThrow(()
				-> new CustomExceptions("Property not found"));
		
		//Validate Room
		Room room = roomRepository.findById(dto.getRoomId()).orElseThrow(() 
					-> new CustomExceptions("Room not found"));
		
				
		if(room.getStatus() == RoomStatus.OCCUPIED) {
			throw new CustomExceptions("Room already occupied");
		}
		Guest primaryGuest = guestRepository.findByMobile(dto.getMobile()).orElse(null);
		
		if(primaryGuest == null) {//If First Time CheckIn
			savePrimaryGuest(primaryGuest, dto, property);
		}
		
		//Set And Save CheckinDetails
		return checkInMapper.toDto( saveCheckinDetails(dto, property, room) );
	}

	private CheckIn saveCheckinDetails(CheckInDto dto, Property property, Room room) {
		List<CheckInGuestList> guests =
		        checkInGuestListMapper.toEntityList(dto.getPersons());

		CheckIn checkIn = CheckIn.builder()
		        .property(property)
		        .room(room)
		        .adultCount( guests.size() )
		        .build();

		guests.forEach(g -> g.setCheckIn(checkIn));

		checkIn.setGuests(guests);

		return checkInRepository.save(checkIn);
		
	}

	private void savePrimaryGuest(Guest primaryGuest, CheckInDto dto, Property property) {
		primaryGuest = Guest.builder()
	            .property( property )
	            .fullName(dto.getFullName())
	            .mobile(dto.getMobile())
	            .gender(dto.getGender())
	            .address(dto.getAddress())
	            .city(dto.getCity())
	            .state(dto.getState())
	            .build();

	    guestRepository.save(primaryGuest);
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
