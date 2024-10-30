package com.hostease.tallerHostease.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditHospedajeDTO {
    @NotBlank(message = "La descripcion no puede estar vacio")
    private String descripcion;
    @NotBlank(message = "La imagen no puede estar vacio")
    private String imagen;
    @NotBlank(message = "El precio no puede estar vacio")
    private String precioPorNoche;
    @NotBlank(message = "La ciudad no puede estar vacio")
    private Long ciudadId;
    @NotBlank(message = "El tipo de hospedaje no puede estar vacio")
    private Long tipoHospedajeId;
    @NotBlank(message = "Los servicios no pueden estar vacio")
    private Set<Long> servicioIds;
}
