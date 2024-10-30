package com.hostease.tallerHostease.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CrearHospedajeDTO {

    @NotBlank(message = "La descripción del hospedaje no puede estar vacía")
    private String descripcion;

    @NotBlank(message = "La imagen del hospedaje no puede estar vacía")
    private String imagen;

    @NotNull(message = "El precio por noche no puede estar vacío")
    private String precioPorNoche;

    @NotNull(message = "La ciudad no puede estar vacía")
    private Long ciudadId;

    @NotNull(message = "El tipo de hospedaje no puede estar vacío")
    private Long tipoHospedajeId;

    private List<Long> serviciosIds;
}
