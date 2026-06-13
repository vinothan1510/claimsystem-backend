package com.vinothan.claimsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_policy")
public class UserPolicy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_policy_id")
    private Long userPolicyId;

    // =========================
    // USER RELATIONSHIP
    // =========================
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    // =========================
    // POLICY RELATIONSHIP
    // =========================
    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private PolicyList policy;

    // =========================
    // BUSINESS FIELDS
    // =========================
    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "premium_paid")
    private BigDecimal premiumPaid;

    // =========================
    // AUTO SET PURCHASE DATE
    // =========================
    @PrePersist
    public void prePersist() {
        this.purchaseDate = LocalDateTime.now();
        this.isActive = true;
    }
}
