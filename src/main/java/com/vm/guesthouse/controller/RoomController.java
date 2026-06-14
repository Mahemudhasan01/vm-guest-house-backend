package com.vm.guesthouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vm.guesthouse.constants.ApiConstants;
import com.vm.guesthouse.constants.MessageConstants;
import com.vm.guesthouse.dto.PageableResponse;
import com.vm.guesthouse.dto.PaginationSortingFilterSearchDTO;
import com.vm.guesthouse.dto.common.ResponseDTO;
import com.vm.guesthouse.dto.room.RoomDto;
import com.vm.guesthouse.service.room.RoomService;

@RestController
@RequestMapping(ApiConstants.VM_GUEST_HOUSE + ApiConstants.REST_API_URL_ROOM)
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@GetMapping()
	public ResponseDTO getAll(PaginationSortingFilterSearchDTO paginationSortingFilterSearchDTO){
		PageableResponse<RoomDto> response = roomService.getAll(paginationSortingFilterSearchDTO);

        if (response.getData() == null || ObjectUtils.isEmpty(response.getData())) {
            return ResponseDTO.builder().statusCode(HttpStatus
            		.NO_CONTENT.value()).message(MessageConstants.NO_DATA_FOUND).isError(Boolean.FALSE).build();
        }

        return ResponseDTO.builder().statusCode(HttpStatus.OK.value())
        		.message(MessageConstants.DATA_RETRIEVED_SUCCESSFULLY).isError(Boolean.FALSE).data(response).build();
	}
}






