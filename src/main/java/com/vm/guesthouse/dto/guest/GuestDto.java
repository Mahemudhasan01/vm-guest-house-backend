package com.vm.guesthouse.dto.guest;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GuestDto {

    private Long propertyId;

    @NotBlank
    private String fullName;

    private String gender;

    private LocalDate dob;

    private String mobile;

    private String email;

    private String address;

    private String city;

    private String state;

    private String country;

    private String pinCode;

    private String gstNo;

    private String companyName;

    private String photoUrl;
}