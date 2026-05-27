package com.vm.guesthouse.dto.auditlog;

import com.vm.guesthouse.enums.Enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditLogRequestDto {

    private Long userId;

    private String moduleName;

    private Enums.AuditActionType actionType;

    private Long entityId;

    private String oldData;

    private String newData;

    private String ipAddress;
}