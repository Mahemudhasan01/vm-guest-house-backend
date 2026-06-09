package com.vm.guesthouse.dto.checkin;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CheckInDto {
    // PROPERTY
	@NotNull
    private Long propertyId = 1L;
    // ROOM
	@NotNull
    private Long roomId = 1L;
    // PRIMARY GUEST
	@NotNull @NotEmpty
    private String fullName;
	@NotNull @NotEmpty
    private String mobile;
    private String gender;
    private String address;
    private String city;
    private String state;
    @NotNull @NotEmpty
    private String idProofNo = "GJ24-704350624";
    // BOOKING
    private Integer adultCount;
    private Integer childCount;
    private Integer extraPersonCount;
    private BigDecimal tariff;
    private BigDecimal advanceAmount;
    private String remarks;
    // ADDITIONAL PERSONS
    @NotNull @NotEmpty
    private List<CheckInGuestListDto> persons;
}