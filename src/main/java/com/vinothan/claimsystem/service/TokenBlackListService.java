package com.vinothan.claimsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinothan.claimsystem.entity.TokenBlackList;
import com.vinothan.claimsystem.repository.TokenBlackListRepository;
import com.vinothan.claimsystem.util.JwtUtil;

@Service
public class TokenBlackListService {

    @Autowired
    private TokenBlackListRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    public void blacklistToken(String token) {

        // Avoid duplicate blacklist entries
        if (repository.existsByToken(token)) {
            return;
        }

        TokenBlackList blacklist = new TokenBlackList(); // ✅ create object

        blacklist.setToken(token);
        blacklist.setExpiry(jwtUtil.getExpiration(token));

        repository.save(blacklist);
    }

    public boolean isBlacklisted(String token) {
        return repository.existsByToken(token);
    }
}
