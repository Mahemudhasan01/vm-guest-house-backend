package com.vm.guesthouse.entity;

import com.vm.guesthouse.entity.base.BaseEntity;
import com.vm.guesthouse.enums.Enums;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "room", uniqueConstraints = {@UniqueConstraint(columnNames = {"property_id", "room_number"})})
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "floor_no")
    private Integer floorNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Enums.RoomStatus status = Enums.RoomStatus.VACANT;

    @Column(name = "notes")
    private String notes;

    @Column(name = "is_active")
    private Boolean isActive = true;
}