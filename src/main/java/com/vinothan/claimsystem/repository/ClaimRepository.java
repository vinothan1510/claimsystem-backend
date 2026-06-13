package com.vinothan.claimsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinothan.claimsystem.entity.Claim;
import com.vinothan.claimsystem.entity.ClaimStatus;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Long> {
	List<Claim> findByUserUserId(Long userId);
	
	List<Claim> findByClaimStatus(ClaimStatus status);
}