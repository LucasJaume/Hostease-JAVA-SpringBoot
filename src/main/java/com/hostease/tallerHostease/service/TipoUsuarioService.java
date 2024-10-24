package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.TipoUsuario;
import com.hostease.tallerHostease.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoUsuarioService implements ITipoUsuarioService {
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public Optional<TipoUsuario> findById(Integer id) {
        return tipoUsuarioRepository.findById(id);
    }

    @Override
    public List<TipoUsuario> findAll() {
        return tipoUsuarioRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {  tipoUsuarioRepository.deleteById(id);  }

    @Override
    public TipoUsuario crearTipoUsuario(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }


    public TipoUsuario editarTipoUsuario(TipoUsuario tipoUsuario, Integer id) {
        Optional<TipoUsuario> existingTipoUsuario = tipoUsuarioRepository.findById(id);
        if (existingTipoUsuario.isPresent()) {
            TipoUsuario updatedTipoUsuario = existingTipoUsuario.get();
            updatedTipoUsuario.setNombre(tipoUsuario.getNombre());
            return tipoUsuarioRepository.save(updatedTipoUsuario);
        }else{
            return null;
        }
    }
}

