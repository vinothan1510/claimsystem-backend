package com.vinothan.claimsystem.service;

import java.util.List;

import com.vinothan.claimsystem.dto.PurchasePolicyRequestDto;
import com.vinothan.claimsystem.dto.PurchasePolicyResponseDto;

public interface UserPolicyService {

    PurchasePolicyResponseDto purchasePolicy(Long userId, PurchasePolicyRequestDto request);
    
    List<PurchasePolicyResponseDto> getUserPolicies(Long userId);

    List<PurchasePolicyResponseDto> getActiveUserPolicies(Long userId);
}

