package com.example.microserviceAuthentification.security.cors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Bean
    public String allowedOrigins() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            log.info("ip adresse : "+ ip.getHostAddress() );
            return "http://" + ip.getHostAddress() + ":4200";
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "http://localhost:4200";
        }
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins())
                .allowedMethods("DELETE", "GET", "POST", "PATCH", "PUT")
                .allowedHeaders("Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control", "Content-Type", "Authorization")
                .exposedHeaders("Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control", "Content-Type", "Authorization")
                .allowCredentials(true).maxAge(3600);
    }
}
