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
@Table(name = "booking_room")
public class BookingRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "checkin_datetime", nullable = false)
    private LocalDateTime checkinDateTime;

    @Column(name = "checkout_datetime")
    private LocalDateTime checkoutDateTime;

    @Column(name = "base_tariff", precision = 10, scale = 2)
    private BigDecimal baseTariff;

    @Column(name = "extra_person_charge", precision = 10, scale = 2)
    private BigDecimal extraPersonCharge;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "final_tariff", precision = 10, scale = 2)
    private BigDecimal finalTariff;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_status")
    private Enums.RoomStatus roomStatus;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;
}