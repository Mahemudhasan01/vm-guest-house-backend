package com.vm.guesthouse.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutDTO {

	private Long id;
	
	@NotEmpty
    private LocalDate checkOutDate;
	
	@NotEmpty
    private String checkOutTime;
	
	private String remarks;
}
