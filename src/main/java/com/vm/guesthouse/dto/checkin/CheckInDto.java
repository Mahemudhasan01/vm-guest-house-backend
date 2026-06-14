package com.vm.guesthouse.dto.checkin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vm.guesthouse.dto.guest.GuestDto;
import com.vm.guesthouse.entity.Guest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckInDto {

    @NotNull
    private Long propertyId;

    @NotNull
    private Long roomId;

    @NotBlank
    private String fullName;

    @NotBlank
    private String mobile;

    private String gender;

    private String address;

    private String city;

    private String state;

    @NotBlank
    private String idProofNo;

    private Integer adultCount;

    private Integer childCount;

    private Integer extraPersonCount;

    private BigDecimal tariff;

    private BigDecimal advanceAmount;

    private String remarks;

    @Valid
    private List<CheckInGuestListDto> persons = new ArrayList<>();
}