package com.hostease.tallerHostease.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditReservaDTO {
    @NotBlank(message = "FehcaCheckIn es obligatorio")
    private LocalDate fechaCheckIn;
    @NotBlank(message = "FechaCheckOut es obligatorio")
    private LocalDate fechaCheckOut;
    @NotBlank(message = "Cantidad de adultos es obligatorio")
    private Integer cantAdultos;
    @NotBlank(message = "Cantidad de niños es obligatorio")
    private Integer cantNinos;
    @NotBlank(message = "Cantidad de mascotas es obligatorio")
    private Integer cantMascotas;

}
