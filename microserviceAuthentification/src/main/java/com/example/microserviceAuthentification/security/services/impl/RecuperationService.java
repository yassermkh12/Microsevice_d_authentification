package com.example.microserviceAuthentification.security.services.impl;

import com.example.microserviceAuthentification.security.entities.User;
import com.example.microserviceAuthentification.security.entitiesDto.UserDto;
import com.example.microserviceAuthentification.security.exceptions.GlobalException;
import com.example.microserviceAuthentification.security.repositories.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecuperationService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void forgotPassword(String email) throws GlobalException{
        log.info("*********************************** forgot password mail sender ***********************************");
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new GlobalException("il n y a pas de user avec cet email")
        );

        log.info("user get email "+ user.getEmail());

        if (user.getEmail().equals(email)) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("password forgot");
//            message.setText(user.getPassword());
            message.setText("vous avez oublier votre password");

            emailSender.send(message);
        }else{
            throw new GlobalException("email n est pas correcte");
        }

    }
}
