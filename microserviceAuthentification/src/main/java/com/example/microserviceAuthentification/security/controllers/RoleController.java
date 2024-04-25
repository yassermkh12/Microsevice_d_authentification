package com.example.microserviceAuthentification.security.controllers;

import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.entitiesDto.RoleDto;
import com.example.microserviceAuthentification.security.services.IRoleService;
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
    private IRoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getAllRole(){
        List<RoleDto> roleDtos = roleService.getAllRole();
        return new ResponseEntity<>(roleDtos, HttpStatus.OK);
    }
    @GetMapping("/by-id/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id){
        RoleDto roleDto = roleService.getRoleById(id);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }
    @PostMapping("/save-role")
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto){
        RoleDto role1 = roleService.addRole(roleDto);
        return new ResponseEntity<>(role1,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-role/{id}")
    public ResponseEntity<Void> deletRole(@PathVariable Long id){
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
