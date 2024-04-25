package com.example.microserviceAuthentification.security.services;

import com.example.microserviceAuthentification.security.entitiesDto.UserDto;

import java.util.List;

public interface IUserService {
    public List<UserDto> getAllUser();
    public UserDto addNewUser(UserDto userDto);
    public UserDto getById(Long id);
    public UserDto getByUserName(String userName);
    public void addRoleToUser(Long userId,Long roleId);
    public void removeRoleToUser(Long userId, Long roleId);
}
