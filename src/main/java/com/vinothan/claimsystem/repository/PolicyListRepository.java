package com.vinothan.claimsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinothan.claimsystem.entity.PolicyList;

public interface PolicyListRepository extends JpaRepository<PolicyList,Long>{
	
	List<PolicyList> findByIsActiveTrue();
}