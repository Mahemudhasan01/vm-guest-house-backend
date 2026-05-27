package com.vm.guesthouse.entity;

import com.vm.guesthouse.entity.base.BaseEntity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "booking_guest")
public class BookingGuest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "is_primary_guest")
    private Boolean isPrimaryGuest = false;
}