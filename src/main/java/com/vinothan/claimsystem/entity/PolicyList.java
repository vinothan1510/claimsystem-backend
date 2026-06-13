package com.vinothan.claimsystem.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="policy_list")
@Data
public class PolicyList extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="policy_id")
	private Long policyId;
	
	@Column(name="policy_name")
	private String policyName;
	
	@Column(name="policy_type")
	private String policyType;
	
	@Column(name="coverage_amount")
	private BigDecimal coverageAmount;
	
	@Column(name="premium_amount")
	private BigDecimal premiumAmount;
	
	private String description;
	
	private Boolean isActive;
	
	
	
	
	
}
