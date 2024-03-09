package com.example.microserviceAuthentification.security.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class JwtService {
    public SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateJwtToken(String userDetails){
        //gestion des dates
        log.info("la generation du token commence");
        Date now = new Date();
        log.info("voici la date du commencement du token : "+ now);
        long longueurExpiration = 3600000;
        Date dateExpiration = new Date(now.getTime() + longueurExpiration);
        log.info("la date de fin du token : "+ dateExpiration);

        Map<String, Object> claims = new HashMap<>();

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails)
                .setIssuedAt(now)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();

        log.info("voici le token : "+ token);

        return token;

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


}
