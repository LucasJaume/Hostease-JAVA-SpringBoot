package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.dto.CrearReservaDTO;
import com.hostease.tallerHostease.dto.EditReservaDTO;
import com.hostease.tallerHostease.model.Hospedaje;
import com.hostease.tallerHostease.model.PKReserva;
import com.hostease.tallerHostease.model.Reserva;
import com.hostease.tallerHostease.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService implements IReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HospedajeService hospedajeService;

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
        Optional<Reserva> optionalReserva = reservaRepository.findById(pkReserva);

        if (!optionalReserva.isPresent()) {
            throw new IllegalArgumentException("Reserva no encontrada.");
        }

        Reserva reserva = optionalReserva.get();

        reservaRepository.delete(reserva);
    }

    @Override
    public Reserva crearReserva(CrearReservaDTO crearReservaDTO){
        Hospedaje hospedaje = hospedajeService.findById(crearReservaDTO.getIdHospedaje())
                .orElseThrow(() -> new IllegalArgumentException("Hospedaje no encontrado."));



        if (!estaDisponible(hospedaje.getID(), crearReservaDTO.getFechaCheckIn(), crearReservaDTO.getFechaCheckOut())) {
            throw new IllegalArgumentException("El hospedaje no está disponible en las fechas seleccionadas.");
        }

       //número de noches
        long dias = ChronoUnit.DAYS.between(crearReservaDTO.getFechaCheckIn(), crearReservaDTO.getFechaCheckOut());

        //precio a BigDecimal
        BigDecimal precioPorNoche = new BigDecimal(hospedaje.getPrecio_por_noche());

        //importe total
        BigDecimal importeTotal = precioPorNoche.multiply(BigDecimal.valueOf(dias));

        Reserva reserva = new Reserva();
        reserva.setPkReserva(new PKReserva(hospedaje.getID(), crearReservaDTO.getIdUsuario()));
        reserva.setFecha_check_in(crearReservaDTO.getFechaCheckIn());
        reserva.setFecha_check_out(crearReservaDTO.getFechaCheckOut());
        reserva.setCant_adultos(crearReservaDTO.getCantAdultos());
        reserva.setCant_niños(crearReservaDTO.getCantNinos());
        reserva.setCant_bebes(crearReservaDTO.getCant_bebes());
        reserva.setCant_mascotas(crearReservaDTO.getCantMascotas());
        reserva.setImporte_total(importeTotal);
        reserva.setFecha_creacion(Instant.now());

        return reservaRepository.save(reserva);
    }

    @Override
    public boolean estaDisponible(Long idHospedaje, LocalDate fechaCheckIn, LocalDate fechaCheckOut) {
        List<Reserva> reservas = reservaRepository.findByIdHospedajeAndFechaCheckInLessThanAndFechaCheckOutGreaterThan(idHospedaje, fechaCheckOut, fechaCheckIn);
        return reservas.isEmpty(); // retorna true si no hay reservas = esta disponible
    }


    @Override
    public  Reserva editReserva(EditReservaDTO editReservaDTO, PKReserva pkReserva) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(pkReserva);

        if (!optionalReserva.isPresent()) {
            throw new IllegalArgumentException("Reserva no encontrada.");
        }

        Reserva reserva = optionalReserva.get();

        //restricción de 24 horas antes del check-in
        if (reserva.getFecha_check_in().minus(1, ChronoUnit.DAYS).isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se puede modificar la reserva a menos de 24 horas del check-in.");
        }

        //disponibilidad en las nuevas fechas
        if (!estaDisponible(pkReserva.getIdHospedaje(), editReservaDTO.getFechaCheckIn(), editReservaDTO.getFechaCheckOut())) {
            throw new IllegalArgumentException("El hospedaje no está disponible en las nuevas fechas seleccionadas.");
        }

        reserva.setFecha_check_in(editReservaDTO.getFechaCheckIn());
        reserva.setFecha_check_out(editReservaDTO.getFechaCheckOut());
        reserva.setCant_adultos(editReservaDTO.getCantAdultos());
        reserva.setCant_niños(editReservaDTO.getCantNinos());
        reserva.setCant_mascotas(editReservaDTO.getCantMascotas());

        //nuevo importe total
        long dias = ChronoUnit.DAYS.between(editReservaDTO.getFechaCheckIn(), editReservaDTO.getFechaCheckOut());
        BigDecimal precioPorNoche = new BigDecimal(hospedajeService.findById(pkReserva.getIdHospedaje()).orElseThrow().getPrecio_por_noche());
        BigDecimal importeTotal = precioPorNoche.multiply(BigDecimal.valueOf(dias));
        reserva.setImporte_total(importeTotal);
        reserva.setFecha_modificacion(Instant.now());

        return reservaRepository.save(reserva);
    }

}
