package com.vinothan.claimsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="claim_document")
@Data
public class ClaimDocument {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long documentId;
		
		private String documentName;
		private String documentPath;
		
		@ManyToOne
		@JoinColumn(name="claims_id")
		private Claim claim;

}
