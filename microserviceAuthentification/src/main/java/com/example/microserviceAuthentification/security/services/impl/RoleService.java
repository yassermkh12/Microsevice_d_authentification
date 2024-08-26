package com.example.microserviceAuthentification.security.services.impl;

import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.entitiesDto.RoleDto;
import com.example.microserviceAuthentification.security.exceptions.GlobalException;
import com.example.microserviceAuthentification.security.repositories.IRoleRepository;
import com.example.microserviceAuthentification.security.services.IRoleService;
import com.example.microserviceAuthentification.security.transformers.RoleTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;


    public List<RoleDto> getAllRole(){
        List<Role> roles = roleRepository.findAll();
        return RoleTransformer.entityToDtoList(roles);
    }

    public RoleDto getRoleById(Long id){
        Role role = roleRepository.findById(id).orElse(null);
        return RoleTransformer.entityToDto(role);
    }

    public RoleDto addRole(RoleDto roleDto){
       Role role = RoleTransformer.dtoToEntity(roleDto);
       role = roleRepository.save(role);
       return RoleTransformer.entityToDto(role);
    }

    public RoleDto updateRole(Long id, RoleDto roleDtoUpdate) throws GlobalException{
        Role role = roleRepository.findById(id).orElse(null);
        if(role != null){
            role.setName(roleDtoUpdate.getName());

            roleRepository.save(role);
            return RoleTransformer.entityToDto(role);
        }else {
            throw new GlobalException("il n y a pas de role avec id : "+ id);
        }
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }
}
