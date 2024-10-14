package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.TipoHospedaje;
import com.hostease.tallerHostease.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface ITipoHospedajeService {
    public Optional<TipoHospedaje> findById(Long id);

    public List<TipoHospedaje> findAll();

    public void deleteByid(Long id);

    public TipoHospedaje createTipoHospedaje(TipoHospedaje tipoHospedaje);

    public TipoHospedaje editTipoHospedaje(TipoHospedaje tipoHospedaje, Long id);
}
