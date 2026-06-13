package com.vinothan.claimsystem.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PurchasePolicyRequestDto {
	
	private Long policyId;
	private LocalDate expiryDate;

}
