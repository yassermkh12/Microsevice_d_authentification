package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.services.impl.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/jwt/test")
public class JwtTestController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    //test de la generation du token
    @GetMapping("/getToken/{username}")
    public ResponseEntity<Void> getToken(@PathVariable String username){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String generateToken = jwtService.generateJwtToken(userDetails);
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
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        jwtService.isTokenValid(token,userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/userDetailsService/{username}")
    public ResponseEntity<UserDetails> testUserDetailsService(@PathVariable String username){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        log.info("userDetails : "+ userDetails);
        log.info("userDetails.username : "+ userDetails.getUsername());
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }
}
