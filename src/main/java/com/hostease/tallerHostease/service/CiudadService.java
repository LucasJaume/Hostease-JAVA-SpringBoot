package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.model.Ciudad;
import com.hostease.tallerHostease.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService implements ICiudadService {

@Autowired
    private CiudadRepository ciudadRepository;

    public Optional<Ciudad> findById(Long id) {return  ciudadRepository.findById(id);}

    public List<Ciudad> findAll() {
        return ciudadRepository.findAll();
    }

    @Override
    public void deleteByid(Long id) {
        ciudadRepository.deleteById(id);
    }

    public  Ciudad createCiudad(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    public Ciudad editCiudad(Ciudad ciudad, Long id) {
        Optional<Ciudad> existingCiudad = ciudadRepository.findById(id);
        if (existingCiudad.isPresent()) {
            Ciudad updatedCiudad = existingCiudad.get();
            updatedCiudad.setNombre(ciudad.getNombre());
            return ciudadRepository.save(updatedCiudad);
        }else {
            return null;
        }
    }
}
