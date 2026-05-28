package com.vm.guesthouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vm.guesthouse.constants.ApiConstants;
import com.vm.guesthouse.dto.checkin.CheckInRequestDto;
import com.vm.guesthouse.dto.common.ResponseDTO;
import com.vm.guesthouse.dto.utils.ApplicationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiConstants.VM_GUEST_HOUSE + ApiConstants.REST_API_URL_CHECKIN)
public class CheckInController {
	
	@PostMapping()
	public ResponseDTO<CheckInRequestDto> save(@Valid @RequestBody CheckInRequestDto checkInRequestDto, BindingResult result){
		if (result.hasErrors()) {
			return ResponseDTO.<CheckInRequestDto>builder().statusCode(HttpStatus.BAD_REQUEST.value())
					.errorMessage(ApplicationUtils.getErrors(result)).isError(Boolean.TRUE).build();
		}
		
		return ResponseDTO.<CheckInRequestDto>builder().statusCode(HttpStatus.OK.value())
				.message("Checkin Successfully!!").isError(Boolean.FALSE)
				.data(checkInRequestDto).build();
	}
}
