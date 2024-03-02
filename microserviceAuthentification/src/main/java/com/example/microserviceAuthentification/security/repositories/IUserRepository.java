package com.example.microserviceAuthentification.security.repositories;

import com.example.microserviceAuthentification.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
