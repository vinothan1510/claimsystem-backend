package com.vinothan.claimsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vinothan.claimsystem.dto.ClaimDocumentResponseDto;
import com.vinothan.claimsystem.service.ClaimDocumentService;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {


	    @Autowired
	    private ClaimDocumentService claimDocumentService;

	    // ✅ USER uploads PDF
	    @PreAuthorize("hasRole('USER')")
	    @PostMapping("/upload")
	    public ResponseEntity<ClaimDocumentResponseDto> upload(
	            @RequestParam Long claimId,
	            @RequestParam MultipartFile file) {

	        return new ResponseEntity<>(
	                claimDocumentService.uploadDocument(claimId, file),
	                HttpStatus.CREATED
	        );
	    }

	    // ✅ CLAIM OFFICER downloads PDF
	    @PreAuthorize("hasRole('CLAIM_OFFICER')")
	    @GetMapping("/download/{documentId}")
	    public ResponseEntity<Resource> download(
	            @PathVariable Long documentId) {

	        Resource resource =
	                claimDocumentService.downloadDocument(documentId);

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"document.pdf\"")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(resource);
	    }
	}