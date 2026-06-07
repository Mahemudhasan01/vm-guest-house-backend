package com.vm.guesthouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vm.guesthouse.constants.ApiConstants;
import com.vm.guesthouse.dto.checkin.CheckInGuestListDto;
import com.vm.guesthouse.dto.checkin.CheckInDto;
import com.vm.guesthouse.dto.common.ResponseDTO;
import com.vm.guesthouse.dto.utils.ApplicationUtils;
import com.vm.guesthouse.service.checkin.CheckInService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiConstants.VM_GUEST_HOUSE + ApiConstants.REST_API_URL_CHECKIN)
public class CheckInController {
	@Autowired
	private CheckInService checkInService;
	
	@PostMapping()
	public ResponseDTO<CheckInDto> save(@Valid @RequestBody CheckInDto checkInRequestDto, BindingResult result){
		if (result.hasErrors()) {
			return ResponseDTO.<CheckInDto>builder().statusCode(HttpStatus.BAD_REQUEST.value())
					.errorMessage(ApplicationUtils.getErrors(result)).isError(Boolean.TRUE).build();
		}
		
		return ResponseDTO.<CheckInDto>builder().statusCode(HttpStatus.OK.value())
				.message("Checkin Successfully!!").isError(Boolean.FALSE)
				.data( checkInService.save(checkInRequestDto) ).build();
	}
}
