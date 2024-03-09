package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.services.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt/test")
public class JwtTestController {
    @Autowired
    private JwtService jwtService;
    //test de la generation du token
    @GetMapping("/getToken/{username}")
    public ResponseEntity<Void> getToken(@PathVariable String username){
        String generateToken = jwtService.generateJwtToken(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //test de l extraction des donnees depuis le token
    @GetMapping("/getUsername/{token}")
    public ResponseEntity<String> getUsername(@PathVariable String token){
        String username = jwtService.extractUsername(token);
        return new ResponseEntity<>(username,HttpStatus.OK);
    }
    //validation du token
    @GetMapping("/validationToken/{token}/{username}")
    public ResponseEntity<Void> validationToken(@PathVariable String token,@PathVariable String username){
        jwtService.isTokenValid(token,username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
