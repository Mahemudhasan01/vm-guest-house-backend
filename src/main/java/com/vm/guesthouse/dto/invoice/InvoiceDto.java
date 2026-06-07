package com.vm.guesthouse.dto.invoice;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvoiceDto {

    private Long bookingId;

    private String invoiceNo;

    private BigDecimal subtotal;

    private BigDecimal discountAmount;

    private BigDecimal taxAmount;

    private BigDecimal grandTotal;

    private String remarks;
}