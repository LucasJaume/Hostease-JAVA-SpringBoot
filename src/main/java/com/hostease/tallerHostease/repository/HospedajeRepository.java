package com.hostease.tallerHostease.repository;

import com.hostease.tallerHostease.model.Hospedaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedajeRepository extends JpaRepository<Hospedaje, Long> {

}
