package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.services.impl.RecuperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recuperation")
public class RecuperationController {
    @Autowired
    private RecuperationService recuperationService;

    @PostMapping("/{email}")
    public ResponseEntity<Void> forgotPassword(@PathVariable String email){
        recuperationService.forgotPassword(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
