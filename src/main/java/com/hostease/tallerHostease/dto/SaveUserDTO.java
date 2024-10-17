package com.hostease.tallerHostease.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SaveUserDTO {
    private String username;
    private String password;
    private String email;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private List<Long> tipoUsuarioIds;
}

