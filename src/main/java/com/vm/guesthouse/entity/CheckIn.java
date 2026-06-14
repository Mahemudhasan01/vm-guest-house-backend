package com.vm.guesthouse.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vm.guesthouse.entity.base.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    
    @OneToMany(
            mappedBy = "checkIn",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CheckInGuestList> persons = new ArrayList<>();

    private LocalDateTime checkInDateTime;

    private Integer adultCount;

    private Integer childCount;

    private Integer extraPersonCount;

    private BigDecimal tariff;

    private BigDecimal advanceAmount;

    private String remarks;

}