package com.vm.guesthouse.dto.bookingguest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingGuestDto {

    private Long bookingId;

    private String fullName;

    private String gender;

    private Integer age;

    private String mobile;

    private String photoUrl;

    private Boolean isPrimaryGuest;
}