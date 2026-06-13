package com.vinothan.claimsystem.util;



import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

@Component
@Data
public class JwtUtil {

    // 🔐 Secret key (keep it safe, move to application.properties later)
    private static final String SECRET_KEY ="vinothansecretkey1234567890ThisIsForMyPrivateProject";

    // ⏰ Token validity (10 hours)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    // 🔹 Generate JWT
    public String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // 🔹 Validate token
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 🔹 Extract username (email)
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 🔹 Extract role
    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // 🔹 Internal method to parse claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

	public Date getExpiration(String token) {
		// TODO Auto-generated method stub
		return getClaims(token).getExpiration();
	}

	
}


