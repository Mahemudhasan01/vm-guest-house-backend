package com.vm.guesthouse.dto.bookingroom;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingRoomRequestDto {

    private Long bookingId;

    private Long roomId;

    private LocalDateTime checkinDateTime;

    private LocalDateTime checkoutDateTime;

    private BigDecimal baseTariff;

    private BigDecimal extraPersonCharge;

    private BigDecimal discountAmount;

    private BigDecimal finalTariff;

    private String remarks;
}