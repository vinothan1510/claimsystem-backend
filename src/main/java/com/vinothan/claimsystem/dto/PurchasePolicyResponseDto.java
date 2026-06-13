package com.vinothan.claimsystem.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PurchasePolicyResponseDto {
	
	private Long userPolicyId;
	private String policyName;
	private LocalDate expiryDate;
	private Boolean isActive;

}