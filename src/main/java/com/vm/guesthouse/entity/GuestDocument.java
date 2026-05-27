package com.vm.guesthouse.entity;

import com.vm.guesthouse.entity.base.BaseEntity;
import com.vm.guesthouse.enums.Enums;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "guest_document")
public class GuestDocument extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private Enums.DocumentType documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "document_image_url")
    private String documentImageUrl;

    @Column(name = "ocr_raw_text", columnDefinition = "TEXT")
    private String ocrRawText;

    @Column(name = "verified")
    private Boolean verified = false;
}