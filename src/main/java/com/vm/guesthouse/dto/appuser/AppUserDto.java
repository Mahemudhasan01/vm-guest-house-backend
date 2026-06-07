package com.vm.guesthouse.dto.appuser;

import com.vm.guesthouse.enums.Enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDto {

    private Long propertyId;

    private String fullName;

    private String username;

    private String password;

    private Enums.UserRole role;

    private String mobile;

    private String email;
}