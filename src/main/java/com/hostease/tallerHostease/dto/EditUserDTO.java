package com.hostease.tallerHostease.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditUserDTO {
    @NotNull(message = "Username no puede estar vacio")
    private String username;
    @NotNull(message = "Contraseña no puede estar vacio")
    private String password;
    @NotNull(message = "Correo no puede estar vacio")
    @Email(message = "El correo debe ser valido")
    private String email;
    @NotNull(message = "Nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "Apellido no puede estar vacio")
    private String apellido;
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @JsonProperty(value = "fecha_nacimiento")
    private LocalDate fecha_nacimiento;

}
