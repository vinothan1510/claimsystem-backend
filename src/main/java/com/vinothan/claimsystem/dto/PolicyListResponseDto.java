package com.vinothan.claimsystem.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PolicyListResponseDto {
	
	
	private Long policyId;
		
	private String policyName;

	private String policyType;
	
	private BigDecimal coverageAmount;
	
	private BigDecimal premiumAmount;
	
	private String description;
	
	
}