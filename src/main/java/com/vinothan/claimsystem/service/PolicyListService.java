package com.vinothan.claimsystem.service;

import java.util.List;

import com.vinothan.claimsystem.dto.PolicyListResponseDto;

public interface PolicyListService {
	List<PolicyListResponseDto> getAvailablePolicies();
	
	
}