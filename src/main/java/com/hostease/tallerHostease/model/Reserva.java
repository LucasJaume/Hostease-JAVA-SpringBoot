package com.hostease.tallerHostease.model;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @EmbeddedId
    private PKReserva pkReserva;
    private LocalDate fecha_check_out;
    private LocalDate fecha_check_in;
    private Integer cant_niños;
    private Integer cant_adultos;
    private Integer cant_bebes;
    private Integer cant_mascotas;
    private BigDecimal importe_total;
    private Instant fecha_creacion;
    private Instant fecha_modificacion;
}