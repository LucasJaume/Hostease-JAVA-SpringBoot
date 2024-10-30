package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.dto.EditServicioDTO;
import com.hostease.tallerHostease.model.Servicio;

import java.util.List;
import java.util.Optional;

public interface IServicioService {
    public Optional<Servicio> findById(Long id);

    public List<Servicio> findAll();

    public void deleteById(Long id);

    public Servicio createServicio(Servicio servicio);

    public Servicio editServicio(EditServicioDTO editServicioDTO, Long id);
}
