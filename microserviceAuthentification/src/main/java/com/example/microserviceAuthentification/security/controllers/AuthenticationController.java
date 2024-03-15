package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.authentications.AuthenticationRequest;
import com.example.microserviceAuthentification.security.authentications.AuthenticationResponse;
import com.example.microserviceAuthentification.security.authentications.ResgisterRequest;
import com.example.microserviceAuthentification.security.services.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
           @RequestBody ResgisterRequest resgisterRequest
    ){
        return ResponseEntity.ok(authenticationService.register(resgisterRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.auhenticate(authenticationRequest));
    }
}