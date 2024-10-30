package com.hostease.tallerHostease.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class SaveUserDTO {
    @NotBlank(message = "Username no puede estar vacio")
    private String username;
    @NotBlank(message = "Contraseña no puede estar vacio")
    private String password;
    @NotBlank(message = "Correo no puede estar vacio")
    @Email(message = "El correo debe ser valido")
    private String email;
    @NotBlank(message = "Nombre no puede estar vacio")
    private String nombre;
    @NotBlank(message = "Apellido no puede estar vacio")
    private String apellido;

    @NotBlank(message = "La fecha de nacimiento no puede ser nula")
    @JsonProperty(value = "fecha_nacimiento")
    private LocalDate fecha_nacimiento;

    private Long tipoUsuarioIds;
}

