package com.example.microserviceAuthentification.security.entitiesDto;

import com.example.microserviceAuthentification.security.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
//    private Roles role;
    private Set<Role> roles;
}
