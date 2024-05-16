package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.services.IRecuperationService;
import com.example.microserviceAuthentification.security.services.impl.RecuperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recuperation")
public class RecuperationController {
    @Autowired
    private IRecuperationService recuperationService;

    @PostMapping("/{email}")
    public ResponseEntity<Void> forgotPassword(@PathVariable String email){
        recuperationService.forgotPassword(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update-password-by-email/{email}/{password}")
    public ResponseEntity<Void> updatePasswordByEmail(@PathVariable String email,@PathVariable String password){
        recuperationService.updatePasswordByEmail(email,password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/verify-code/{email}/{code}")
    public ResponseEntity<Void> verifyCode(@PathVariable String email,@PathVariable String code){
        recuperationService.verifyCode(email,code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
