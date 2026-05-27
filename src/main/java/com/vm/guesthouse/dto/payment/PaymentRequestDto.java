package com.vm.guesthouse.dto.payment;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import com.vm.guesthouse.enums.Enums;

@Getter
@Setter
public class PaymentRequestDto {

    private Long bookingId;

    private Long invoiceId;

    private Enums.PaymentMethod paymentMethod;

    private BigDecimal amount;

    private String referenceNo;

    private String remarks;
}