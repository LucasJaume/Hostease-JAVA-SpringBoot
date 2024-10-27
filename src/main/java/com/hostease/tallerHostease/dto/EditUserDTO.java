package com.hostease.tallerHostease.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDTO {
    @NotNull(message = "Username no puede estar vacio")
    private String username;
    @NotNull(message = "Contraseña no puede estar vacia")
    private String password;
    @NotNull(message = "Correo no puede estar vacio")
    @Email(message = "El correo debe ser valido")
    private String email;
    @NotNull(message = "Nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "Apellido no puede estar vacio")
    private String apellido;
}
