package com.vm.guesthouse.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.vm.guesthouse.entity.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CheckIn extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Property property;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Guest primaryGuest;

    private LocalDateTime checkInDateTime;

    private Integer adultCount;

    private Integer childCount;

    private Integer extraPersonCount;

    private BigDecimal tariff;

    private BigDecimal advanceAmount;

    private String remarks;

}