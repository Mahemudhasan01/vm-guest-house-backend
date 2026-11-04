package com.vm.guesthouse.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vm.guesthouse.entity.base.BaseEntity;
import com.vm.guesthouse.enums.Enums;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CheckIn extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Property property;

    @ManyToOne
    private Room room;

    @ManyToOne
    private PrimaryGuest primaryGuest;
    
	@OneToMany(mappedBy = "checkIn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraPerson> persons = new ArrayList<>();

	@Column(nullable = false)
    private LocalDate checkInDate;
	@Column(nullable = false)
    private String checkInTime;
	
	@Column(nullable = true)
    private LocalDate checkOutDate;
	@Column(nullable = true)
    private String checkOutTime;
	
	@Enumerated(EnumType.STRING)
	private Enums.StayStatus stayStatus;

    private Integer adultCount;

    private Integer childCount;

    private Integer extraPersonCount;

    @Column(nullable = false)
    private BigDecimal tariff;

    private BigDecimal advanceAmount;

    private String remarks;

}