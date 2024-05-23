package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.services.IRecuperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recuperation-security")
public class RecuperationSecurityController {
    @Autowired
    private IRecuperationService recuperationService;

    @GetMapping("/forgotPassword/{email}")
    public ResponseEntity<Void> forgotPassword(@PathVariable String email){
        recuperationService.forgotPassword(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/update-password-by-email/{email}/{password}")
    public ResponseEntity<Void> updatePasswordByEmail(@PathVariable String email,@PathVariable String password){
        recuperationService.updatePasswordByEmail(email,password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/verify-code/{email}/{code}")
    public ResponseEntity<Void> verifyCode(@PathVariable String email,@PathVariable String code){
        recuperationService.verifyCode(email,code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
