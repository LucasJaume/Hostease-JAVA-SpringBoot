package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.Hospedaje;

import java.util.List;
import java.util.Optional;

public interface IHospedajeService {

    public Optional<Hospedaje> findById(Long id);

    public List<Hospedaje> findAll();

    public void deleteById(Long id);

    public Hospedaje createHospedaje(Hospedaje hospedaje);

    public Hospedaje editHospedaje(Hospedaje hospedaje, Long id);

}
