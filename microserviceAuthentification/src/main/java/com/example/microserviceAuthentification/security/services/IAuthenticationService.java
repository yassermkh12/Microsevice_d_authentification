package com.example.microserviceAuthentification.security.services;

import com.example.microserviceAuthentification.security.authentications.AuthenticationRequest;
import com.example.microserviceAuthentification.security.authentications.AuthenticationResponse;
import com.example.microserviceAuthentification.security.authentications.ResgisterRequest;

public interface IAuthenticationService {
    public AuthenticationResponse register(ResgisterRequest resgisterRequest);
    public AuthenticationResponse auhenticate(AuthenticationRequest authenticationRequest);
}
