package com.example.microserviceAuthentification.security.repositories;

import com.example.microserviceAuthentification.security.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEtudiantRepository extends JpaRepository<Etudiant,Long> {
}
