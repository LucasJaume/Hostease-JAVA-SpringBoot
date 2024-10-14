package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.PKReserva;
import com.hostease.tallerHostease.model.Reserva;
import com.hostease.tallerHostease.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService implements IReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public Optional<Reserva> findById(PKReserva pkReserva) {
        return reservaRepository.findById(pkReserva);
    }

    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public void deleteByid(PKReserva pkReserva) {
        reservaRepository.deleteById(pkReserva);
    }

    @Override
    public Reserva crearReserva(Reserva reserva){
        return reservaRepository.save(reserva);
    }

    @Override
    public  Reserva editReserva(Reserva reserva, PKReserva pkReserva) {
        Optional<Reserva> existingReserva = reservaRepository.findById(pkReserva);
        if(existingReserva.isPresent()){
            Reserva updatedReserva = existingReserva.get();
            updatedReserva.setFecha_check_out(reserva.getFecha_check_out());
            updatedReserva.setFecha_check_in(reserva.getFecha_check_in());
            updatedReserva.setCant_niños(reserva.getCant_niños());
            updatedReserva.setCant_adultos(reserva.getCant_adultos());
            updatedReserva.setCant_bebes(reserva.getCant_bebes());
            updatedReserva.setCant_mascotas(reserva.getCant_mascotas());
            updatedReserva.setImporte_total(reserva.getImporte_total());
            updatedReserva.setFecha_modificacion(Instant.now());
            return reservaRepository.save(updatedReserva);
        } else {
            return null;
        }
    }





}
