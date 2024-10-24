package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.dto.SaveUserDTO;
import com.hostease.tallerHostease.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public Optional<Usuario> findById(Long id);

    public List<Usuario> findAll();

    public void deleteByid(Long id);

    public Usuario createUsuario(Usuario usuario);

    public Usuario editUsuario(SaveUserDTO usuario, Long id);

    public Optional<Usuario> findByUsername(String email);
}
