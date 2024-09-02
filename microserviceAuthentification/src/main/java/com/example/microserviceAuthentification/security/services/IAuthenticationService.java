package com.example.microserviceAuthentification.security.services;

import com.example.microserviceAuthentification.security.authentications.*;
import com.example.microserviceAuthentification.security.exceptions.GlobalException;

public interface IAuthenticationService {
    public AuthenticationResponse register(ResgisterRequest resgisterRequest) throws GlobalException;
    public AuthenticationResponse auhenticate(AuthenticationRequest authenticationRequest) throws GlobalException;
    public AuthenticationResponse registerEtudiant(RegisterRequestEtudiant resgisterRequest) throws GlobalException;
    public AuthenticationResponse registerEmploye(RegisterRequestEmploye resgisterRequest) throws GlobalException;
}
