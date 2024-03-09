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
    @GetMapping("/{username}")
    public ResponseEntity<Void> getToken(@PathVariable String username){
        String generateToken = jwtService.generateJwtToken(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
