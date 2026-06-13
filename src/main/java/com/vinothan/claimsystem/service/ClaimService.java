package com.vinothan.claimsystem.service;

import java.util.List;

import com.vinothan.claimsystem.dto.ClaimRequestDto;
import com.vinothan.claimsystem.dto.ClaimResponseDto;

public interface ClaimService {
	
	//User services
	ClaimResponseDto submitClaim(Long userId,ClaimRequestDto request);
	
	List<ClaimResponseDto> getClaimsByUser(Long userId);
	
	//Claim officer services
	
	List<ClaimResponseDto> getAllClaims();
	
	ClaimResponseDto approveClaim(Long claimId);
	
	ClaimResponseDto rejectClaim(Long claimId,String remarks);
}
