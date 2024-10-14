package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.PKReserva;
import com.hostease.tallerHostease.model.Reserva;
import com.hostease.tallerHostease.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IReservaService {
    public Optional<Reserva> findById(PKReserva pkReserva);

    public List<Reserva> findAll();

    public void deleteByid(PKReserva pkReserva);

    public Reserva crearReserva(Reserva reserva);

    public Reserva editReserva(Reserva reserva, PKReserva pkReserva);
}
