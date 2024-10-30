package com.hostease.tallerHostease.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDTO {
    @NotBlank(message = "Username no puede estar vacio")
    private String username;
    @NotBlank(message = "Contraseña no puede estar vacia")
    private String password;
    @NotBlank(message = "Correo no puede estar vacio")
    @Email(message = "El correo debe ser valido")
    private String email;
    @NotBlank(message = "Nombre no puede estar vacio")
    private String nombre;
    @NotBlank(message = "Apellido no puede estar vacio")
    private String apellido;
}
