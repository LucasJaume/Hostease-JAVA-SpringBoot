package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.dto.CrearReservaDTO;
import com.hostease.tallerHostease.dto.EditReservaDTO;
import com.hostease.tallerHostease.model.PKReserva;
import com.hostease.tallerHostease.model.Reserva;
import com.hostease.tallerHostease.model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReservaService {
    public Optional<Reserva> findById(PKReserva pkReserva);

    public List<Reserva> findAll();

    public void deleteByid(PKReserva pkReserva);

    public Reserva crearReserva(CrearReservaDTO crearReservaDTO);

    public Reserva editReserva(EditReservaDTO editReservaDTO, PKReserva pkReserva);

    public boolean estaDisponible(Long idHospedaje, LocalDate fechaCheckIn, LocalDate fechaCheckOut);
}
