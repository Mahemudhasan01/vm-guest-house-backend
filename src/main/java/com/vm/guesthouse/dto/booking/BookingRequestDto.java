package com.vm.guesthouse.dto.booking;

import com.vm.guesthouse.enums.Enums;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingRequestDto {

    private Long propertyId;

    private Long guestId;

    private String bookingNo;

    private LocalDateTime checkinDateTime;

    private LocalDateTime expectedCheckoutDateTime;

    private Enums.BookingSource bookingSource;

    private Integer adultCount;

    private Integer childCount;

    private Integer extraPersonCount;

    private String remarks;
}