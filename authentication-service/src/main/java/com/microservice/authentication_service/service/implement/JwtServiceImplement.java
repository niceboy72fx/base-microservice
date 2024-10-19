package com.microservice.authentication_service.service.implement;

import com.microservice.authentication_service.common.TokenType;
import com.microservice.authentication_service.exception.InvalidDataException;
import com.microservice.authentication_service.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImplement implements JwtService {

    @Value("${jwt.accessKey}")
    private String accessKey;

    @Value("${jwt.refreshKey}")
    private String refreshKey;

    @Value("${jwt.expiryMinutes}")
    private long expiryMinutes;

    @Value("${jwt.expiryDay}")
    private long expiryDay;

    private Key getKey(TokenType type) {
        switch (type) {
            case ACCESS_TOKEN -> {
                return Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessKey));
            }
            case REFRESH_TOKEN -> {
                return Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshKey));
            }
            default -> throw new InvalidDataException("Invalid token type");
        }
    }
    @Override
    public String generateToken(Long userId, String username, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> bodyClaims = new HashMap<>();
        bodyClaims.put("user_id", userId);
        bodyClaims.put("role", authorities);
        return generateToken(bodyClaims, username);
    }

    private String generateToken(Map<String, Object> bodyClaims, String username) {
        return Jwts.builder()
                .setClaims(bodyClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * expiryDay))
                .signWith(getKey(TokenType.ACCESS_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }
    @Override
    public String generateRefreshToken(Long userId, String username, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> bodyClaims = new HashMap<>();
        bodyClaims.put("user_id", userId);
        bodyClaims.put("role", authorities);
        return generateRefreshToken(bodyClaims, username);
    }

    private String generateRefreshToken(Map<String, Object> bodyClaims, String username) {
        return Jwts.builder()
                .setClaims(bodyClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * expiryDay))
                .signWith(getKey(TokenType.REFRESH_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }

    private <T> T extractClaim(String token, TokenType type, Function<Claims, T> claimResolver) {
        final Claims claims = extraAllClaim(token, type);
        return claimResolver.apply(claims);
    }


    private Claims extraAllClaim(String token, TokenType type) {
        return Jwts.parserBuilder().setSigningKey(getKey(type)).build().parseClaimsJws(token).getBody();
    }

    @Override
    public String extractUsername(String token, TokenType type) {
        return extractClaim(token, type, Claims::getSubject);
    }


    @Override
    public boolean isValid(String token, TokenType type, String username) {
        final String user = extractUsername(token, type);
        return (user.equals(username) && !isTokenExpired(token, type));
    }


    private boolean isTokenExpired(String token, TokenType type) {
        return extractExpiration(token, type).before(new Date());
    }


    private Date extractExpiration(String token, TokenType type) {
        return extractClaim(token, type, Claims::getExpiration);
    }
}
