package com.vm.guesthouse.dto.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {

    private Long id;

    private String name;

    private String code;

    private String address;

    private String city;

    private String state;

    private String mobile;

    private String email;

    private String gstNo;

    private Boolean isActive;
}