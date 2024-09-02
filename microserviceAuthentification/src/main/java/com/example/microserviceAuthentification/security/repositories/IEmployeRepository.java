package com.example.microserviceAuthentification.security.repositories;

import com.example.microserviceAuthentification.security.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeRepository extends JpaRepository<Employe,Long> {
}
