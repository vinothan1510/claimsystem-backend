package com.vinothan.claimsystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinothan.claimsystem.dto.ClaimRequestDto;
import com.vinothan.claimsystem.dto.ClaimResponseDto;
import com.vinothan.claimsystem.entity.Claim;
import com.vinothan.claimsystem.entity.ClaimStatus;
import com.vinothan.claimsystem.entity.UserPolicy;
import com.vinothan.claimsystem.entity.Users;
import com.vinothan.claimsystem.exception.ResourceNotFoundException;
import com.vinothan.claimsystem.repository.ClaimRepository;
import com.vinothan.claimsystem.repository.PolicyListRepository;
import com.vinothan.claimsystem.repository.UserPolicyRepository;
import com.vinothan.claimsystem.repository.UserRepository;

@Service
public class ClaimServiceImpl implements ClaimService{
	
	@Autowired
	private ClaimRepository claimRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PolicyListRepository policyListRepository;
	
	@Autowired
	private UserPolicyRepository userPolicyRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// user services
	
	@Override
	public ClaimResponseDto submitClaim(Long userId, ClaimRequestDto request) {

	    // 1. Validate user
	    Users user = userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	    // 2. Fetch ACTIVE purchased policy
	    UserPolicy userPolicy = userPolicyRepository
	            .findByUserAndUserPolicyIdAndIsActiveTrue(user, request.getUserPolicyId())
	            .orElseThrow(() -> new ResourceNotFoundException(
	                    "Active purchased policy not found"));

	    // 3. Check expiry
	    if (userPolicy.getExpiryDate().isBefore(LocalDate.now())) {
	        throw new IllegalStateException("Policy has expired");
	    }

	    // 4. Validate claim amount
	    if (request.getClaimAmount()
	            .compareTo(userPolicy.getPolicy().getCoverageAmount()) > 0) {
	        throw new IllegalArgumentException(
	                "Claim amount exceeds policy coverage");
	    }

	    // 5. Create claim
	    Claim claim = new Claim();
	    claim.setUser(user);
	    claim.setPolicyList(userPolicy.getPolicy());
	    claim.setClaimAmount(request.getClaimAmount());
	    claim.setClaimReason(request.getClaimReason());
	    claim.setClaimStatus(ClaimStatus.SUBMITTED);

	    Claim savedClaim = claimRepository.save(claim);

	    // 6. Response
	    ClaimResponseDto response = new ClaimResponseDto();
	    response.setClaimId(savedClaim.getClaimId());
	    response.setClaimAmount(savedClaim.getClaimAmount());
	    response.setClaimReason(savedClaim.getClaimReason());
	    response.setClaimStatus(savedClaim.getClaimStatus().name());

	    return response;
	}
	
	
	// user can track the status of the claims
	public List<ClaimResponseDto> getClaimsByUser(Long userId){
		return claimRepository.findByUserUserId(userId)
				.stream().map(claim->modelMapper.map(claim, ClaimResponseDto.class))
				.collect(Collectors.toList());
		
	}
	
	// claim officer services
	
	public List<ClaimResponseDto> getAllClaims(){
		return claimRepository.findAll().stream()
				.map(claim->modelMapper.map(claim, ClaimResponseDto.class))
				.collect(Collectors.toList());
	}
	
	
	public ClaimResponseDto approveClaim(Long claimId) {
		Optional<Claim>op=claimRepository.findById(claimId);
		Claim claim=op.orElseThrow(()-> new ResourceNotFoundException("Claim not found"));
		
		claim.setClaimStatus(ClaimStatus.APPROVED);
	
		return modelMapper.map(claimRepository.save(claim),ClaimResponseDto.class );
		
	}
	
	public ClaimResponseDto rejectClaim(Long claimId,String remarks) {
		Optional<Claim>op=claimRepository.findById(claimId);
		Claim claim=op.orElseThrow(()-> new ResourceNotFoundException("Claim not found"));
		
		claim.setClaimStatus(ClaimStatus.REJECTED);
		claim.setOfficerRemark(remarks);
		
		return modelMapper.map(claimRepository.save(claim),ClaimResponseDto.class );
		
	}

	
	

}
