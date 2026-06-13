package com.vinothan.claimsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinothan.claimsystem.dto.PolicyListResponseDto;
import com.vinothan.claimsystem.dto.PurchasePolicyRequestDto;
import com.vinothan.claimsystem.dto.PurchasePolicyResponseDto;
import com.vinothan.claimsystem.service.PolicyListService;
import com.vinothan.claimsystem.service.UserPolicyService;

@RestController
@RequestMapping("api/policies")
public class PolicyListController {
	@Autowired
	private PolicyListService policyListService;
	
	@Autowired
	private UserPolicyService userPolicyService;
	
	@PreAuthorize("hasAnyRole('USER','CLAIM_OFFICER')")
	@GetMapping
	public ResponseEntity<List<PolicyListResponseDto>> getAvailablePolicies(){
		
		return ResponseEntity.ok(policyListService.getAvailablePolicies());
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/purchase/{userId}")
	
	public ResponseEntity<PurchasePolicyResponseDto> purchasePolicy(@PathVariable Long userId, @RequestBody PurchasePolicyRequestDto request){
		PurchasePolicyResponseDto response= userPolicyService.purchasePolicy(userId, request);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	 // =========================
    // VIEW ALL PURCHASED POLICIES
    // =========================
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchasePolicyResponseDto>> getUserPolicies(
            @PathVariable Long userId) {

        List<PurchasePolicyResponseDto> policies =
                userPolicyService.getUserPolicies(userId);

        return ResponseEntity.ok(policies);
    }

    // =========================
    // VIEW ACTIVE POLICIES (FOR CLAIM)
    // =========================
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<PurchasePolicyResponseDto>> getActiveUserPolicies(
            @PathVariable Long userId) {

        List<PurchasePolicyResponseDto> policies =
                userPolicyService.getActiveUserPolicies(userId);

        return ResponseEntity.ok(policies);
    }
}
