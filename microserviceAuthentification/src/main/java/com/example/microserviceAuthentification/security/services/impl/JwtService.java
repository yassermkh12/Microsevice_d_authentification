package com.example.microserviceAuthentification.security.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class JwtService {
    @Autowired
    private UserDetailsService userDetails;
    public SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateJwtToken(UserDetails userDetails){
        //gestion des dates
        log.info("la generation du token commence");
        Date now = new Date();
        log.info("voici la date du commencement du token : "+ now);
        long longueurExpiration = 36000;
        Date dateExpiration = new Date(now.getTime() + longueurExpiration);
        log.info("la date de fin du token : "+ dateExpiration);

        Map<String, Object> claims = new HashMap<>();

        claims.put("roles", userDetails.getAuthorities());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();

        log.info("voici le token : "+ token);

        return token;
    }

    public String generateJwtRefrechToken(UserDetails userDetails){
        //gestion des dates
        log.info("la generation du token refrech commence");
        Date now = new Date();
        log.info("voici la date du commencement du refrech token : "+ now);
        long longueurExpiration = 3600000;
        Date dateExpiration = new Date(now.getTime() + longueurExpiration);
        log.info("la date de fin du refrech token : "+ dateExpiration);

        Map<String, Object> claims = new HashMap<>();

        claims.put("roles", userDetails.getAuthorities());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();

        log.info("voici le refrech token : "+ token);

        return token;
    }

    public String generateRefrechTokenFromToken(String token){
        String username = extractUsername(token);

        return generateJwtRefrechToken(userDetails.loadUserByUsername(username));
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){

        log.info("extraction du user depuis le token commence");
        Claims claims = Jwts
                .parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        log.info("les claims sont : "+ claims);

        String username = claims.getSubject();
        log.info("le username depuis le token est : "+ username);

        return username;
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        log.info("la validation du token commence depuis le service");
        String usernameToken = extractUsername(token);
        log.info("username dans le token : "+ usernameToken);
        log.info("username dans l userDetails : "+ userDetails.getUsername());
        return (usernameToken.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        log.info("l expiration du token");
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        Claims claims = Jwts
                .parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Date expirationToken = claims.getExpiration();

        return expirationToken;
    }
}
