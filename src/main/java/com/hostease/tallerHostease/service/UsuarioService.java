package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.Usuario;
import com.hostease.tallerHostease.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository UsuarioRepository;

    //Autowired

    @Override
    public Optional<Usuario> findById(Long id) {
        return UsuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return UsuarioRepository.findAll();
    }

    @Override
    public void deleteByid(Long id) { UsuarioRepository.deleteById(id); }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return UsuarioRepository.save(usuario);
    }

    @Override
    public Usuario editUsuario(Usuario usuario, Long id) {
        Optional<Usuario> existingUsuario = UsuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
            Usuario updatedUsuario = existingUsuario.get();
            updatedUsuario.setUsername(usuario.getUsername());
            updatedUsuario.setPassword(usuario.getPassword());
            updatedUsuario.setEmail(usuario.getEmail());
            updatedUsuario.setNombre(usuario.getNombre());
            updatedUsuario.setApellido(usuario.getApellido());
            updatedUsuario.setFecha_nacimiento(usuario.getFecha_nacimiento());
            updatedUsuario.setFecha_modificacion(Instant.now());
            updatedUsuario.setTipoUsuarios(usuario.getTipoUsuarios());
            return UsuarioRepository.save(updatedUsuario);
        }else {
            return null;
        }
    }
}
