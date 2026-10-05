package com.vm.guesthouse.dto.checkin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vm.guesthouse.dto.guest.PrimaryGuestDto;
import com.vm.guesthouse.entity.PrimaryGuest;
import com.vm.guesthouse.entity.Room;
import com.vm.guesthouse.enums.Enums;

import jakarta.persistence.Column;
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
    
    private Room room;

    private Integer adultCount;

    private Integer childCount = 0;

    private Integer extraPersonCount = 0;

    private BigDecimal tariff;

    private BigDecimal advanceAmount = BigDecimal.ZERO;
    
    @NotNull
    private LocalDate checkInDate;
    
    @NotBlank
    private String checkInTime;
    
    private LocalDate checkOutDate;
    
    private String checkOutTime;
	
    @NotEmpty
	private Enums.StayStatus stayStatus;

    private String remarks;

    @Valid
    private List<ExtraPersonDto> persons;
}