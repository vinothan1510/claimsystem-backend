package com.vinothan.claimsystem.dto;

import lombok.Data;

@Data
public class ClaimDocumentResponseDto {
	private Long documentId;
	
	
	
	private String documentName;
	
	private String downloadUrl;
}
