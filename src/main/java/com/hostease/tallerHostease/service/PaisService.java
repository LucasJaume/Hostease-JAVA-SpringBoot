package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.Pais;
import com.hostease.tallerHostease.model.Usuario;
import com.hostease.tallerHostease.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService implements IPaisService {

    @Autowired
    private PaisRepository paisRepository;

    @Override
    public Optional<Pais> findById(Long id) {
        return paisRepository.findById(id);
    }

    @Override
    public List<Pais> findAll() {
        return paisRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {  paisRepository.deleteById(id);  }

    @Override
    public Pais crearPais (Pais pais) { return paisRepository.save(pais); }

    @Override
    public Pais editarPais(Pais pais, Long id) {
        Optional<Pais> exsistingPais = paisRepository.findById(id);
        if (exsistingPais.isPresent()) {
            Pais updatePais = exsistingPais.get();
            updatePais.setNombre(pais.getNombre());
           return paisRepository.save(updatePais);
        } else {
            return null;
        }
    }
}

