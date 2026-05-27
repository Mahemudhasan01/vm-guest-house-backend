package com.vm.guesthouse.entity;

import com.vm.guesthouse.entity.base.BaseEntity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "room_type")
public class RoomType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "base_tariff", precision = 10, scale = 2)
    private BigDecimal baseTariff;

    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @Column(name = "extra_person_charge", precision = 10, scale = 2)
    private BigDecimal extraPersonCharge;

    @Column(name = "is_ac")
    private Boolean isAc = false;

    @Column(name = "is_active")
    private Boolean isActive = true;
}