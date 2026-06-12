package com.vm.guesthouse.service.room.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vm.guesthouse.dto.FilterCondition;
import com.vm.guesthouse.dto.PageableResponse;
import com.vm.guesthouse.dto.PaginationSortingFilterSearchDTO;
import com.vm.guesthouse.dto.room.RoomDto;
import com.vm.guesthouse.entity.Room;
import com.vm.guesthouse.repository.room.RoomRepository;
import com.vm.guesthouse.service.FilterBuilderService;
import com.vm.guesthouse.service.mapper.room.RoomMapper;
import com.vm.guesthouse.service.room.RoomService;
import com.vm.guesthouse.utils.ApplicationUtils;
import com.vm.guesthouse.utils.GenericJpaSpecificationBuilder;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private FilterBuilderService<Room> filterBuilderService;
	@Autowired
	private RoomMapper roomMapper;
	@Override
	public RoomDto save(RoomDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RoomDto update(RoomDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RoomDto get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PageableResponse<RoomDto> getAll(PaginationSortingFilterSearchDTO paginationSortingFilterSearchDTO) {
		GenericJpaSpecificationBuilder<Room> specificationBuilder = new GenericJpaSpecificationBuilder<Room>();
		List<FilterCondition> filterConditionAnd = filterBuilderService.createFilterCondition(paginationSortingFilterSearchDTO.getFilterAnd());
		List<FilterCondition> filterConditionOr = filterBuilderService.createFilterCondition(paginationSortingFilterSearchDTO.getFilterOr());
		
        Specification<Room> specification = specificationBuilder.addConditions(filterConditionAnd, filterConditionOr);

        Pageable pageable = ApplicationUtils.createPageable(paginationSortingFilterSearchDTO);
        
        Page<Room> paginatedEmployee = roomRepository.findAll(specification, pageable);
        
        Page<RoomDto> page = paginatedEmployee.map( roomMapper::toDto );
        
        PageableResponse<RoomDto> pageableResponse = new PageableResponse<RoomDto>();
        
        return pageableResponse.convert(new PageImpl<>(page.getContent(), pageable, page.getTotalPages() ) );
		
	}
	
	

	
	
}
