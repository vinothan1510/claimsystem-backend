package com.vinothan.claimsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinothan.claimsystem.entity.TokenBlackList;


public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {

boolean existsByToken(String token);
}
