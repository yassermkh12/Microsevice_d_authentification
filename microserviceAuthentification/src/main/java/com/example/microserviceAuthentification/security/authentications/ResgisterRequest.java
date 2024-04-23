package com.example.microserviceAuthentification.security.authentications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResgisterRequest {
    private String email;
    private String username;
    private String password;
}
