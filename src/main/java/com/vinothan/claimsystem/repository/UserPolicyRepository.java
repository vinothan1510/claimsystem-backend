package com.vinothan.claimsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinothan.claimsystem.entity.UserPolicy;
import com.vinothan.claimsystem.entity.Users;

public interface UserPolicyRepository extends JpaRepository<UserPolicy, Long> {

    // Get all policies purchased by a user
    List<UserPolicy> findByUser(Users user);

    // Get active policy for claim submission
    Optional<UserPolicy> findByUserAndUserPolicyIdAndIsActiveTrue(Users user, Long userPolicyId);

    // Get all active policies of a user
    List<UserPolicy> findByUserAndIsActiveTrue(Users user);
}