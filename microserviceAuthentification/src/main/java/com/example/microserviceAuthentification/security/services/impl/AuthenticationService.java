package com.example.microserviceAuthentification.security.services.impl;

import com.example.microserviceAuthentification.security.authentications.AuthenticationRequest;
import com.example.microserviceAuthentification.security.authentications.AuthenticationResponse;
import com.example.microserviceAuthentification.security.authentications.ResgisterRequest;
import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.entities.User;
import com.example.microserviceAuthentification.security.repositories.IUserRepository;
import lombok.Builder;
//import lombok.var;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AuthenticationService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse register(ResgisterRequest resgisterRequest){
        log.info("*** le processus de REGISTER commence ***");
        User user = new User();
        user.setUserName(resgisterRequest.getUsername());
        user.setPassword(resgisterRequest.getPassword());
//        user.setRole(resgisterReques);
        user.setRole(Role.USER);

        log.info("l utilisateur depuis user : "+ user);
        log.info("l utilisateur depuis registerRequest : "+ resgisterRequest);
        userRepository.save(user);

        var jwtToken =  jwtService.generateJwtToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);

        log.info("authentication reponse est : "+ authenticationResponse);

        return authenticationResponse;
    }

    public AuthenticationResponse auhenticate(AuthenticationRequest authenticationRequest){
        log.info("*** le processus d authentification commence ***");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        log.info("UsernamePasswordAuthenticationToken : "+ (new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())));
        User user = userRepository.findByUserName(authenticationRequest.getUsername());


        String jwtToken =  jwtService.generateJwtToken(user);

        String jwtRefraicheToken = jwtService.generateRefrechTokenFromToken(jwtToken);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtToken, jwtRefraicheToken);

        return authenticationResponse;
    }
}
