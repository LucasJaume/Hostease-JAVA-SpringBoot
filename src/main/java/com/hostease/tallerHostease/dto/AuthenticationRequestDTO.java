package com.hostease.tallerHostease.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestDTO {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;


}
