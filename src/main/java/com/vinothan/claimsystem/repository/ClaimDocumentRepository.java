package com.vinothan.claimsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinothan.claimsystem.entity.ClaimDocument;

@Repository
public interface ClaimDocumentRepository extends JpaRepository<ClaimDocument,Long> {
	
	List<ClaimDocument> findByClaimClaimId(Long claimId);
}
