package com.example.microserviceAuthentification.security.services.impl;

import com.example.microserviceAuthentification.security.authentications.AuthenticationRequest;
import com.example.microserviceAuthentification.security.authentications.AuthenticationResponse;
import com.example.microserviceAuthentification.security.authentications.ResgisterRequest;
import com.example.microserviceAuthentification.security.entities.Role;
import com.example.microserviceAuthentification.security.entities.User;
import com.example.microserviceAuthentification.security.exceptions.GlobalException;
import com.example.microserviceAuthentification.security.repositories.IRoleRepository;
import com.example.microserviceAuthentification.security.repositories.IUserRepository;
//import lombok.var;
import com.example.microserviceAuthentification.security.services.IAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleRepository roleRepository;

    @Transactional
    public AuthenticationResponse register(ResgisterRequest resgisterRequest) throws GlobalException {

        if (userRepository.findByUserName(resgisterRequest.getUsername()) != null) {
            log.info("username deja utiliser");
            throw new GlobalException("username deja utiliser");
        }
        log.info("*** le processus de REGISTER commence ***");
        User user = new User();
        Role role = roleRepository.findById(2L).orElse(null);

        user.setUserName(resgisterRequest.getUsername());
        user.setPassword(passwordEncoder.encode(resgisterRequest.getPassword()));
        user.getRoles().add(role);

        log.info("l utilisateur depuis user : "+ user);
        log.info("l utilisateur depuis registerRequest : "+ resgisterRequest);
        userRepository.save(user);

        String jwtToken =  jwtService.generateJwtToken(user);
        String jwtRefrecheToken = jwtService.generateRefrechTokenFromToken(jwtToken);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        authenticationResponse.setRefrechToken(jwtRefrecheToken);

        log.info("authentication reponse est : "+ authenticationResponse);

        return authenticationResponse;

    }

    public AuthenticationResponse auhenticate(AuthenticationRequest authenticationRequest) throws GlobalException{
        log.info("*** le processus d authentification commence ***");

        if(userRepository.findByUserName(authenticationRequest.getUsername()) == null){
            log.info("le username n existe pas dans notre base de donnees");
            throw new GlobalException("le username n existe pas dans notre base de donnees");
        }else {
            if (!passwordEncoder.matches(authenticationRequest.getPassword(),userRepository.findByUserName(authenticationRequest.getUsername()).getPassword())){
                log.info("Mot de passe incorrect pour l'utilisateur : " + authenticationRequest.getUsername());
                throw new GlobalException("Mot de passe incorrect pour l'utilisateur : " + authenticationRequest.getUsername());
            }
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        log.info("UsernamePasswordAuthenticationToken : "+ (new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())));
        User user = userRepository.findByUserName(authenticationRequest.getUsername());


        String jwtToken =  jwtService.generateJwtToken(user);

        String jwtRefrecheToken = jwtService.generateRefrechTokenFromToken(jwtToken);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        authenticationResponse.setRefrechToken(jwtRefrecheToken);

        return authenticationResponse;
    }
}
