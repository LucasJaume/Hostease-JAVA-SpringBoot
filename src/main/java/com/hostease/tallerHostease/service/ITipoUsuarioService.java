package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface ITipoUsuarioService {
    public Optional<TipoUsuario> findById(Long id);

    public List<TipoUsuario> findAll();

    public void deleteById(Long id);

    public TipoUsuario crearTipoUsuario(TipoUsuario tipoUsuario);

    public TipoUsuario editarTipoUsuario(TipoUsuario tipoUsuario, Long id);
}
