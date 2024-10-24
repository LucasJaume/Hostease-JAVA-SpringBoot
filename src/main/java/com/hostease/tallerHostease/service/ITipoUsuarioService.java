package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface ITipoUsuarioService {
    public Optional<TipoUsuario> findById(Integer id);

    public List<TipoUsuario> findAll();

    public void deleteById(Integer id);

    public TipoUsuario crearTipoUsuario(TipoUsuario tipoUsuario);

    public TipoUsuario editarTipoUsuario(TipoUsuario tipoUsuario, Integer id);
}
