package com.vm.guesthouse.dto.checkin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vm.guesthouse.dto.guest.PrimaryGuestDto;
import com.vm.guesthouse.entity.PrimaryGuest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckInDto {

	private Long id;
	
    private Long propertyId;

    private Long roomId;

    @Valid
    private PrimaryGuestDto primaryGuest;

    private Integer adultCount;

    private Integer childCount;

    private Integer extraPersonCount;

    private BigDecimal tariff;

    private BigDecimal advanceAmount;

    private String remarks;

    @Valid
    private List<ExtraPersonDto> persons;
}