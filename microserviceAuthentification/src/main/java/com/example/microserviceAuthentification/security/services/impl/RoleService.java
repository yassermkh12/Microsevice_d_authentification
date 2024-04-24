package com.example.microserviceAuthentification.security.services.impl;

import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private IRoleRepository roleRepository;


    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id){
        return roleRepository.findById(id).orElse(null);
    }

    public Role addRole(Role role){
       return roleRepository.save(role);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }
}
