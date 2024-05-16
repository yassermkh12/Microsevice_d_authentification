package com.example.microserviceAuthentification.security.config;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Slf4j
public class EmailConfig {
    @org.springframework.beans.factory.annotation.Value("${spring.mail.host}")
    private String host;

    @org.springframework.beans.factory.annotation.Value("${spring.mail.port}")
    private int port;

    @org.springframework.beans.factory.annotation.Value("${spring.mail.username}")
    private String username;

    @org.springframework.beans.factory.annotation.Value("${spring.mail.password}")
    private String password;

    @Bean
    public JavaMailSender javaMailSender() {
        log.info("**************************** java mail config ****************************");
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        log.info("mail config : "+ mailSender);
        return mailSender;
    }
}
