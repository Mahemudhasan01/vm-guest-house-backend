package com.vm.guesthouse.entity;

import com.vm.guesthouse.entity.base.BaseEntity;
import com.vm.guesthouse.enums.Enums;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @Column(name = "booking_no", unique = true, nullable = false)
    private String bookingNo;

    @Column(name = "checkin_datetime", nullable = false)
    private LocalDateTime checkinDateTime;

    @Column(name = "expected_checkout_datetime")
    private LocalDateTime expectedCheckoutDateTime;

    @Column(name = "actual_checkout_datetime")
    private LocalDateTime actualCheckoutDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private Enums.BookingStatus bookingStatus = Enums.BookingStatus.CHECKED_IN;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_source")
    private Enums.BookingSource bookingSource = Enums.BookingSource.WALK_IN;

    @Column(name = "adult_count")
    private Integer adultCount = 1;

    @Column(name = "child_count")
    private Integer childCount = 0;

    @Column(name = "extra_person_count")
    private Integer extraPersonCount = 0;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;
}