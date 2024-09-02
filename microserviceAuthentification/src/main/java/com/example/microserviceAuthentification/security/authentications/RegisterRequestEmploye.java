package com.example.microserviceAuthentification.security.authentications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestEmploye extends ResgisterRequest{
    private String major;
}
