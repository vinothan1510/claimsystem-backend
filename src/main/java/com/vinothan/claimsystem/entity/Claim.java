package com.vinothan.claimsystem.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="claims")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Claim extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long claimId;
	private BigDecimal claimAmount;
	private String claimReason;
	
	@Enumerated(EnumType.STRING)
	private ClaimStatus claimStatus;
	
	private String officerRemark;
	
	@ManyToOne
	@JoinColumn(name="policy_id")
	private PolicyList policyList;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
	
	
	
}
