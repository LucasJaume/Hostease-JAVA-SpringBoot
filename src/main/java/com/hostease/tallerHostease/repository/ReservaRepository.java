package com.hostease.tallerHostease.repository;

import com.hostease.tallerHostease.model.PKReserva;
import com.hostease.tallerHostease.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, PKReserva> {
    @Query("SELECT r FROM Reserva r WHERE r.pkReserva.idHospedaje = :idHospedaje " +
            "AND (r.fecha_check_in < :fechaCheckOut AND r.fecha_check_out > :fechaCheckIn)")
    List<Reserva> findByIdHospedajeAndFechaCheckInLessThanAndFechaCheckOutGreaterThan(
            @Param("idHospedaje") Long idHospedaje,
            @Param("fechaCheckOut") LocalDate fechaCheckOut,
            @Param("fechaCheckIn") LocalDate fechaCheckIn);

}
