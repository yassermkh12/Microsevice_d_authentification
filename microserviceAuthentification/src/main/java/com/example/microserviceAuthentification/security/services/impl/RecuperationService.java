package com.example.microserviceAuthentification.security.services.impl;

import com.example.microserviceAuthentification.security.entities.User;
import com.example.microserviceAuthentification.security.entitiesDto.UserDto;
import com.example.microserviceAuthentification.security.exceptions.GlobalException;
import com.example.microserviceAuthentification.security.repositories.IUserRepository;
import com.example.microserviceAuthentification.security.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class RecuperationService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserService userService;

    public void forgotPassword(String email) throws GlobalException{
        log.info("*********************************** forgot password mail sender ***********************************");
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new GlobalException("il n y a pas de user avec cet email")
        );
        log.info("user get email "+ user.getEmail());

        String verificationCode = UUID.randomUUID().toString();
        user.setVerificationCode(verificationCode);
        user.setVerificationCodeExpiration(LocalDateTime.now().plusHours(1)); // Code expires in 1 hour
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Verification Code");
        message.setText("Your verification code is: " + verificationCode);

        emailSender.send(message);
    }

    public void verifyCode(String email, String code) throws GlobalException{
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new GlobalException("il n y a pas de user avec cet email")
        );

        if (user.getVerificationCode() == null || !user.getVerificationCode().equals(code) ||
                user.getVerificationCodeExpiration().isBefore(LocalDateTime.now())){
            throw new GlobalException("le code est invalid");
        }

        user.setVerificationCode(null);
        user.setVerificationCodeExpiration(null);
        userRepository.save(user);
    }

    public void updatePasswordByEmail(String email, String password) throws GlobalException{
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new GlobalException("il n y a pas de user avec cet email")
        );

        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
