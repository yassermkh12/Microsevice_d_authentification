package com.example.microserviceAuthentification.security.repositories;

import com.example.microserviceAuthentification.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Long> {
}
