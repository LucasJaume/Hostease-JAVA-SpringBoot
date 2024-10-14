package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.Pais;

import java.util.List;
import java.util.Optional;

public interface IPaisService {

    public Optional<Pais> findById(Long id);

    public List<Pais> findAll();

    public void deleteById(Long id);

    public Pais crearPais(Pais pais);

    public Pais editarPais(Pais pais, Long id);
}
