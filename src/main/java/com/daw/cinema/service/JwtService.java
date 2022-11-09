package com.daw.cinema.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService implements Serializable {
  private final Key secretKey;

  public JwtService(@Value("${jwt.secret}") String secret) {
    secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(UserDetails userDetails) {
    var rolesString =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

    return createToken(userDetails.getUsername(), Map.of("roles", rolesString));
  }

  private String createToken(String subject, Map<String, Object> claims) {
    return Jwts.builder()
        .setSubject(subject)
        .addClaims(claims)
        .setExpiration(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
        .signWith(secretKey)
        .compact();
  }

  public Map<String, Object> parseClaims(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build().parseClaimsJws(token).getBody();
  }

  public LocalDateTime getExpirationDate(String token) {
    Integer exp = (Integer) parseClaims(token).get("exp");

    return Instant.ofEpochSecond(exp).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }
}
