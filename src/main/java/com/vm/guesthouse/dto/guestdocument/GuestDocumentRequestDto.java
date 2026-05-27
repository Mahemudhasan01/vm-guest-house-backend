package com.vm.guesthouse.dto.guestdocument;

import com.vm.guesthouse.enums.Enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestDocumentRequestDto {

    private Long guestId;

    private Enums.DocumentType documentType;

    private String documentNumber;

    private String documentImageUrl;

    private String ocrRawText;
}