package com.vinothan.claimsystem.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ClaimRequestDto {
	private Long userPolicyId;
	private BigDecimal claimAmount;
	private String claimReason;
}