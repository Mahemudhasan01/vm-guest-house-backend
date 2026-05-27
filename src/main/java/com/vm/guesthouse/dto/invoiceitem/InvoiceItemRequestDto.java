package com.vm.guesthouse.dto.invoiceitem;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.vm.guesthouse.enums.Enums;

@Getter
@Setter
public class InvoiceItemRequestDto {

    private Long invoiceId;

    private Enums.InvoiceItemType itemType;

    private String description;

    private BigDecimal quantity;

    private BigDecimal rate;

    private BigDecimal amount;

    private LocalDateTime serviceDateTime;

    private String remarks;
}