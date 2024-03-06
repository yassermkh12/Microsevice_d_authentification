package com.example.microserviceAuthentification.security.securityConfig;

import com.example.microserviceAuthentification.security.filters.JwtAuthenticationFlilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(csrf -> csrf
                                .disable() // Désactiver CSRF
                        )
                        .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/save-users").permitAll()
                            .anyRequest().permitAll()
                            )
                        .formLogin(formLogin -> formLogin
                            .loginPage("/login")
                            .permitAll()
                        )
                        .addFilter(new JwtAuthenticationFlilter())

                        .rememberMe(Customizer.withDefaults());

        return http.build();
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//
//    }
}
