package com.hostease.tallerHostease.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponseDTO {

    private String token;
    private String username;
    private String role;
    private  Long id;
}