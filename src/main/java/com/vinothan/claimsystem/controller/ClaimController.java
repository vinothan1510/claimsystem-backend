package com.vinothan.claimsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinothan.claimsystem.dto.ClaimRequestDto;
import com.vinothan.claimsystem.dto.ClaimResponseDto;
import com.vinothan.claimsystem.service.ClaimService;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
	@Autowired
	private ClaimService claimService;
	
	// users service for claims
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/submit/{userId}")
	public ResponseEntity<ClaimResponseDto>submitClaim(@PathVariable Long userId,@RequestBody ClaimRequestDto request){
		ClaimResponseDto response=claimService.submitClaim(userId, request);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user/{userId}/track")
	public ResponseEntity<List<ClaimResponseDto>> getClaimsbyUser(@PathVariable Long userId){
		List<ClaimResponseDto>claims=claimService.getClaimsByUser(userId);
		
		return ResponseEntity.ok(claims);
	}
	
	// claim officer service for claims
	@PreAuthorize("hasRole('CLAIM_OFFICER')")
	@GetMapping
	public ResponseEntity<List<ClaimResponseDto>> getAllClaims(){
		
		List<ClaimResponseDto> response=claimService.getAllClaims();
		return ResponseEntity.ok(response);
	}
	
	@PreAuthorize("hasRole('CLAIM_OFFICER')")
	@PutMapping("/approve/{claimId}")
	public ResponseEntity<ClaimResponseDto> approveClaim(@PathVariable Long claimId){
		
		ClaimResponseDto response=claimService.approveClaim(claimId);
		return ResponseEntity.ok(response);
	}
	@PreAuthorize("hasRole('CLAIM_OFFICER')")
	@PutMapping("/reject/{claimId}")
	public ResponseEntity<ClaimResponseDto> rejectClaim(@PathVariable Long claimId,@RequestParam String remarks){
		
		ClaimResponseDto response=claimService.rejectClaim(claimId,remarks);
		return ResponseEntity.ok(response);
	}
	
	
	
	
	

}
