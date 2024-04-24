package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.services.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRole(){
        List<Role> roles = roleService.getAllRole();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @GetMapping("/by-id/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){
        Role role = roleService.getRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    @PostMapping("/save-role")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        Role role1 = roleService.addRole(role);
        return new ResponseEntity<>(role1,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-role/{id}")
    public ResponseEntity<Void> deletRole(@PathVariable Long id){
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
