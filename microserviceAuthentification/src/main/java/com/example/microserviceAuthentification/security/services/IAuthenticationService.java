package com.example.microserviceAuthentification.security.services;

import com.example.microserviceAuthentification.security.authentications.AuthenticationRequest;
import com.example.microserviceAuthentification.security.authentications.AuthenticationResponse;
import com.example.microserviceAuthentification.security.authentications.ResgisterRequest;
import com.example.microserviceAuthentification.security.exceptions.GlobalException;

public interface IAuthenticationService {
    public AuthenticationResponse register(ResgisterRequest resgisterRequest) throws GlobalException;
    public AuthenticationResponse auhenticate(AuthenticationRequest authenticationRequest) throws GlobalException;
}
