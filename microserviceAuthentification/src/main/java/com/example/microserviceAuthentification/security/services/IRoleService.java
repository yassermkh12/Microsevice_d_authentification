package com.example.microserviceAuthentification.security.services;

import com.example.microserviceAuthentification.security.entitiesDto.RoleDto;

import java.util.List;

public interface IRoleService {
    public List<RoleDto> getAllRole();
    public RoleDto getRoleById(Long id);
    public RoleDto addRole(RoleDto roleDto);
    public void deleteRole(Long id);
}
