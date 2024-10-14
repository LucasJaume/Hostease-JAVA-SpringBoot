package com.hostease.tallerHostease.repository;

import com.hostease.tallerHostease.model.PKReserva;
import com.hostease.tallerHostease.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, PKReserva> {

}
