package com.vinothan.claimsystem.dto;

import lombok.Data;

@Data
public class PolicyResponse {
	private Long policyId;
	private String policyName;
	private Double coverageAmount;
	private Double premium;
}
