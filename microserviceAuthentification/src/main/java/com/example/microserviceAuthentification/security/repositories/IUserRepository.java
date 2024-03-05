package com.example.microserviceAuthentification.security.repositories;

import com.example.microserviceAuthentification.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IUserRepository extends JpaRepository<User,Long> {
    public User findByUserName(String userName);
}
