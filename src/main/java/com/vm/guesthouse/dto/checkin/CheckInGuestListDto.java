package com.vm.guesthouse.dto.checkin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckInGuestListDto {

    private String fullName;

    private String gender;

    private Integer age;

    private String mobile;

    private Boolean primaryGuest;
}