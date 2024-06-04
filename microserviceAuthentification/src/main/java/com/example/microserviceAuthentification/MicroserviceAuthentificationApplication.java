package com.example.microserviceAuthentification;

import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MicroserviceAuthentificationApplication implements CommandLineRunner {

	@Autowired
	private IRoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAuthentificationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.count() == 0) {
			Role adminRole = new Role(1L,"ADMIN");
			Role userRole = new Role(2L,"USER");
			roleRepository.save(adminRole);
			roleRepository.save(userRole);
		}
	}
}
