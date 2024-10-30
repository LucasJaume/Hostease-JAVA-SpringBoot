package com.hostease.tallerHostease.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditServicioDTO {
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
}
