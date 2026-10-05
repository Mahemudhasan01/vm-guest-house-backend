package com.vm.guesthouse.service.checkin.impl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vm.guesthouse.constants.MessageConstants;
import com.vm.guesthouse.dto.FilterCondition;
import com.vm.guesthouse.dto.PageableResponse;
import com.vm.guesthouse.dto.PaginationSortingFilterSearchDTO;
import com.vm.guesthouse.dto.checkin.CheckInDto;
import com.vm.guesthouse.dto.checkin.ExtraPersonDto;
import com.vm.guesthouse.dto.guest.PrimaryGuestDto;
import com.vm.guesthouse.entity.CheckIn;
import com.vm.guesthouse.entity.ExtraPerson;
import com.vm.guesthouse.entity.PrimaryGuest;
import com.vm.guesthouse.entity.Property;
import com.vm.guesthouse.entity.Room;
import com.vm.guesthouse.enums.Enums.RoomStatus;
import com.vm.guesthouse.exceptions.CustomExceptions;
import com.vm.guesthouse.repository.PrimaryGuest.PrimaryGuestRepository;
import com.vm.guesthouse.repository.checkIn.CheckInRepository;
import com.vm.guesthouse.repository.guest.GuestRepository;
import com.vm.guesthouse.repository.property.PropertyRepository;
import com.vm.guesthouse.repository.room.RoomRepository;
import com.vm.guesthouse.service.FilterBuilderService;
import com.vm.guesthouse.service.checkin.CheckInService;
import com.vm.guesthouse.service.mapper.checkIn.CheckInMapper;
import com.vm.guesthouse.service.mapper.checkInGuestList.CheckInGuestListMapper;
import com.vm.guesthouse.service.mapper.extraPerson.ExtraPersonMapper;
import com.vm.guesthouse.service.mapper.primaryGuest.PrimaryGuestMapper;
import com.vm.guesthouse.utils.ApplicationUtils;
import com.vm.guesthouse.utils.GenericJpaSpecificationBuilder;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class CheckInServiceImpl implements CheckInService{
	@Autowired
	private CheckInMapper checkInMapper;
	@Autowired
	private PrimaryGuestMapper primaryGuestMapper;
	@Autowired
	private PrimaryGuestRepository primaryGuestRepository;
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
	@Autowired
	private ExtraPersonMapper extraPersonMapper;
	@Autowired
	private FilterBuilderService<CheckIn> filterBuilderService;
	
	@Override
	public CheckInDto save(CheckInDto dto) {
		//Validate Property
		Property property = propertyRepository.findByIdAndIsActiveTrue(dto.getPropertyId() ).orElseThrow(()
				-> new CustomExceptions(MessageConstants.PROPERTY_NOT_FOUND, HttpStatus.BAD_REQUEST.value() ));
		
		//Validate Room
		Room room = roomRepository.findById(dto.getRoomId()).orElseThrow(() 
				-> new CustomExceptions(MessageConstants.ROOM_NOT_FOUND, HttpStatus.BAD_REQUEST.value() ));

		 PrimaryGuest primaryGuest = savePrimaryGuest(dto, property);
		
		//Set And Save CheckinDetails
		CheckInDto checkInDto = checkInMapper.toDto( saveCheckinDetails(dto, property, room, primaryGuest) );
		
		room.setStatus(RoomStatus.OCCUPIED);
		roomRepository.save(room);
		
		return checkInDto;
	}

	private PrimaryGuest savePrimaryGuest(CheckInDto dto, Property property) {

	    return guestRepository.save( primaryGuestMapper.toEntity( dto.getPrimaryGuest() ) );
	}
	
	private CheckIn saveCheckinDetails(CheckInDto dto, Property property, Room room, PrimaryGuest primaryGuest) {
		List<ExtraPerson> guests =
		        checkInGuestListMapper.toEntityList(dto.getPersons());

		CheckIn checkIn = CheckIn.builder()
		        .property(property)
		        .room(room)
		        .primaryGuest(primaryGuest)
		        .adultCount( guests.size() )
		        .childCount( dto.getChildCount() )
		        .checkInDate( dto.getCheckInDate() )
		        .checkInTime( dto.getCheckInTime() )
		        .tariff( dto.getTariff() )
		        .advanceAmount( dto.getAdvanceAmount() )
		        .extraPersonCount( dto.getExtraPersonCount() )
		        .remarks( dto.getRemarks() )
		        .build();

		guests.forEach(g -> g.setCheckIn(checkIn));

		checkIn.setPersons(guests);

		return checkInRepository.save(checkIn);
		
	}

	@Override
	public CheckInDto update(CheckInDto dto) {
	    CheckIn checkIn = checkInRepository.findById(dto.getId())
	            .orElseThrow( () -> new CustomExceptions(MessageConstants.CHECKIN_NOT_FOUND, HttpStatus.BAD_REQUEST.value()) );

	    updatePrimaryGuest(checkIn, dto.getPrimaryGuest());

	    updateExtraPersons(checkIn, dto.getPersons());

	    updateCheckInFields(checkIn, dto);

	    return checkInMapper.toDto(checkIn);
	}
	
	private void updateCheckInFields(CheckIn checkIn, CheckInDto dto) {

	    // Room & Property (only if your business allows changing them)
	    // checkIn.setRoom(room);
	    // checkIn.setProperty(property);

	    checkIn.setAdultCount(dto.getAdultCount());
	    checkIn.setChildCount(dto.getChildCount());
	    checkIn.setExtraPersonCount(dto.getExtraPersonCount());

	    checkIn.setTariff(dto.getTariff());
	    checkIn.setAdvanceAmount(dto.getAdvanceAmount());

	    checkIn.setRemarks(dto.getRemarks());
	    checkIn.setCheckInDate( dto.getCheckInDate() );
	    checkIn.setCheckInTime( dto.getCheckInTime() );

	    // Usually check-in time should not change after creation.
	    // Uncomment only if your business requires it.
	    // checkIn.setCheckInDateTime(dto.getCheckInDateTime());
	}

	private void updateExtraPersons(CheckIn checkIn, List<ExtraPersonDto> personDtos) {
	    // Existing persons mapped by ID
	    Map<Long, ExtraPerson> existingPersons = checkIn.getPersons().stream()
	            .filter(person -> person.getId() != null)
	            .collect(Collectors.toMap(ExtraPerson::getId, Function.identity()));

	    List<ExtraPerson> updatedPersons = new ArrayList<>();

	    for (ExtraPersonDto dto : personDtos) {

	        ExtraPerson person;

	        if (dto.getId() == null) {// New Person
	            person = extraPersonMapper.toEntity(dto);
	        } else { // Existing Person
	            person = existingPersons.remove(dto.getId());
	            if (person == null) {
	                throw new CustomExceptions( MessageConstants.EXTRA_PERSON_NOT_FOUND, HttpStatus.BAD_REQUEST.value() );
	            }

	            extraPersonMapper.updateToEntity(dto, person);
	        }
	        person.setCheckIn(checkIn);
	        updatedPersons.add(person);
	    }

	    // Remove deleted persons
	    checkIn.getPersons().clear();

	    // Add updated/new persons
	    checkIn.getPersons().addAll(updatedPersons);
	}

	private void updatePrimaryGuest(CheckIn checkIn, PrimaryGuestDto dto) {
		PrimaryGuest guest = checkIn.getPrimaryGuest();

		primaryGuestMapper.updateToEntity(dto, guest);
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
		GenericJpaSpecificationBuilder<CheckIn> specificationBuilder = new GenericJpaSpecificationBuilder<CheckIn>();
		
		List<FilterCondition> filterConditionAnd = filterBuilderService.createFilterCondition(paginationSortingFilterSearchDTO.getFilterAnd());
		List<FilterCondition> filterConditionOr = filterBuilderService.createFilterCondition(paginationSortingFilterSearchDTO.getFilterOr());
		
        Specification<CheckIn> specification = specificationBuilder.addConditions(filterConditionAnd, filterConditionOr);
        Pageable pageable = ApplicationUtils.createPageable(paginationSortingFilterSearchDTO);
        Page<CheckIn> paginatedEmployee = checkInRepository.findAll(specification, pageable);
        
        Page<CheckInDto> page = paginatedEmployee.map( checkInMapper::toDto );
        
        PageableResponse<CheckInDto> pageableResponse = new PageableResponse<CheckInDto>();
        
        return pageableResponse.convert(new PageImpl<>(page.getContent(), pageable, page.getTotalPages() ) );
	}

	@Override
	public CheckInDto getCurrentGuestByRoomId(Long roomId) {
		CheckInDto checkInDto = checkInMapper.toDto( checkInRepository.findByRoomId(roomId) );
		
		return checkInDto;
	}

}
