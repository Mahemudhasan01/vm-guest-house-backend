package com.vm.guesthouse.dto.guestdocument;

import com.vm.guesthouse.enums.Enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestDocumentDto {

    private Long guestId;

    private Enums.DocumentType documentType;

    private String documentNumber;

    private String documentImageUrl;

    private String ocrRawText;
}