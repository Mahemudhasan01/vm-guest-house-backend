package com.vm.guesthouse.dto.room;

import com.vm.guesthouse.enums.Enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDto {

    private Long id;

    private Long propertyId;
    
    private String propertyName;

    private Long roomTypeId;
    
    private String typeName;

    private String roomNumber;

    private Integer floorNo;

    private Enums.RoomStatus status;

    private String notes;

    private Boolean isActive;
}