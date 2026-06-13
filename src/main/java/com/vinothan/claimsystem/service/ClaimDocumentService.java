package com.vinothan.claimsystem.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.vinothan.claimsystem.dto.ClaimDocumentResponseDto;



public interface ClaimDocumentService {
	
	ClaimDocumentResponseDto uploadDocument(Long claimId,MultipartFile file);
	
	Resource downloadDocument(Long documentId);
	
	

}
