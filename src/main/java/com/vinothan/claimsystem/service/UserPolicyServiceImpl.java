package com.vinothan.claimsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vinothan.claimsystem.dto.PurchasePolicyRequestDto;
import com.vinothan.claimsystem.dto.PurchasePolicyResponseDto;
import com.vinothan.claimsystem.entity.PolicyList;
import com.vinothan.claimsystem.entity.UserPolicy;
import com.vinothan.claimsystem.entity.Users;
import com.vinothan.claimsystem.exception.ResourceNotFoundException;
import com.vinothan.claimsystem.repository.PolicyListRepository;
import com.vinothan.claimsystem.repository.UserPolicyRepository;
import com.vinothan.claimsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPolicyServiceImpl implements UserPolicyService {

    private final UserRepository userRepository;
    private final PolicyListRepository policyListRepository;
    private final UserPolicyRepository userPolicyRepository;

    @Override
    public PurchasePolicyResponseDto purchasePolicy(Long userId, PurchasePolicyRequestDto request) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        PolicyList policy = policyListRepository.findById(request.getPolicyId())
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));

        UserPolicy userPolicy = new UserPolicy();
        userPolicy.setUser(user);
        userPolicy.setPolicy(policy);
        userPolicy.setExpiryDate(request.getExpiryDate());
        userPolicy.setIsActive(true);

        userPolicyRepository.save(userPolicy);

        PurchasePolicyResponseDto response = new PurchasePolicyResponseDto();
        response.setUserPolicyId(userPolicy.getUserPolicyId());
        response.setPolicyName(policy.getPolicyName());
        response.setExpiryDate(userPolicy.getExpiryDate());
        response.setIsActive(userPolicy.getIsActive());

        return response;
    }
    
    // =========================
    // VIEW ALL USER POLICIES
    // =========================
    @Override
    public List<PurchasePolicyResponseDto> getUserPolicies(Long userId) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<UserPolicy> userPolicies = userPolicyRepository.findByUser(user);

        return userPolicies.stream().map(userPolicy -> {
            PurchasePolicyResponseDto dto = new PurchasePolicyResponseDto();
            dto.setUserPolicyId(userPolicy.getUserPolicyId());
            dto.setPolicyName(userPolicy.getPolicy().getPolicyName());
            dto.setExpiryDate(userPolicy.getExpiryDate());
            dto.setIsActive(userPolicy.getIsActive());
            return dto;
        }).toList();
    }

    // =========================
    // VIEW ACTIVE USER POLICIES
    // =========================
    @Override
    public List<PurchasePolicyResponseDto> getActiveUserPolicies(Long userId) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<UserPolicy> activePolicies =
                userPolicyRepository.findByUserAndIsActiveTrue(user);

        return activePolicies.stream().map(userPolicy -> {
            PurchasePolicyResponseDto dto = new PurchasePolicyResponseDto();
            dto.setUserPolicyId(userPolicy.getUserPolicyId());
            dto.setPolicyName(userPolicy.getPolicy().getPolicyName());
            dto.setExpiryDate(userPolicy.getExpiryDate());
            dto.setIsActive(userPolicy.getIsActive());
            return dto;
        }).toList();
    }

}
