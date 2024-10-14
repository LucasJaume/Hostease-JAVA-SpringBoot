package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.Ciudad;

import java.util.List;
import java.util.Optional;

public interface ICiudadService {
    public Optional<Ciudad> findById(Long id);

    public List<Ciudad> findAll();

    public void deleteByid(Long id);

    public Ciudad createCiudad(Ciudad ciudad);

    public Ciudad editCiudad(Ciudad ciudad, Long id);
}

