package com.vm.guesthouse.entity;

import com.vm.guesthouse.entity.base.BaseEntity;
import com.vm.guesthouse.enums.Enums;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "invoice_item")
public class InvoiceItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type")
    private Enums.InvoiceItemType itemType;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "quantity", precision = 10, scale = 2)
    private BigDecimal quantity = BigDecimal.ONE;

    @Column(name = "rate", precision = 12, scale = 2)
    private BigDecimal rate = BigDecimal.ZERO;

    @Column(name = "amount", precision = 12, scale = 2)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "service_datetime")
    private LocalDateTime serviceDateTime;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;
}