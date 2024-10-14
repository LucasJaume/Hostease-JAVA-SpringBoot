package com.hostease.tallerHostease.repository;

import com.hostease.tallerHostease.model.TipoHospedaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoHospedajeRepository extends JpaRepository<TipoHospedaje, Long> {
}
