package com.hostease.tallerHostease.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrearReservaDTO {
    @NotBlank(message = "El IdHospedaje es obligatorio")
    private Long idHospedaje;
    @NotBlank(message = "El idUsuario es obligatorio")
    private Long idUsuario;
    @NotBlank(message = "FechaCheckIn es obligatorio")
    private LocalDate fechaCheckIn;
    @NotBlank(message = "FechaCheckOut es obligatorio")
    private LocalDate fechaCheckOut;
    @NotBlank(message = "Cantidad de adultos es obligatorio")
    private Integer cantAdultos;
    @NotBlank(message = "Cantidad de niños es obligatorio")
    private Integer cantNinos;
    @NotBlank(message = "Cantidad de bebes es obligatorio")
    private Integer cant_bebes;
    @NotBlank(message = "Cantidad de mascotas es obligatorio")
    private Integer cantMascotas;
}
