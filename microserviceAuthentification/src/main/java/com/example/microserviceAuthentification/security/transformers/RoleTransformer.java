package com.example.microserviceAuthentification.security.transformers;

import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.entitiesDto.RoleDto;

import java.util.*;
import java.util.stream.Collectors;

public class RoleTransformer {
    public static RoleDto entityToDto(Role role){
        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setRoleName(role.getRoleName());

        return roleDto;
    }

    public static Role dtoToEntity(RoleDto roleDto){
        Role role = new Role();

        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getRoleName());

        return role;
    }

    public static List<RoleDto> entityToDtoList(List<Role> roles){
        return roles.stream()
                .map(RoleTransformer::entityToDto)
                .collect(Collectors.toList());
    }

    public static List<Role> dtoToEntityList(List<RoleDto> roleDtos){
        return roleDtos.stream()
                .map(RoleTransformer::dtoToEntity)
                .collect(Collectors.toList());
    }
}
