package com.vinothan.claimsystem.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ClaimResponseDto {
	private Long claimId;
	private BigDecimal claimAmount;
	private String claimReason;
	private String claimStatus;
}
