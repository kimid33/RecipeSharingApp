package com.dollop.app.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	
	private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
	
	public String generateToken(Authentication auth) {
		String jwt = Jwts.builder()
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime()+86400000))
				.claim("email", auth.getName())
				.signWith(key).compact();
				
	  return jwt;
	}
	
	public String getEmailFromJwtToken(String jwt) {
		jwt = jwt.substring(7);
		Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
		String email = String.valueOf(claims.get("email"));
		return email;
	}
}
