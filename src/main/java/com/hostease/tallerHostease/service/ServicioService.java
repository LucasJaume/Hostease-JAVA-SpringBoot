package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.dto.EditServicioDTO;
import com.hostease.tallerHostease.model.Servicio;
import com.hostease.tallerHostease.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServicioService implements IServicioService {
    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Optional<Servicio> findById(Long id){ return servicioRepository.findById(id);}

    @Override
    public List<Servicio> findAll(){ return servicioRepository.findAll();}

    @Override
    public void deleteById(Long id) {
        servicioRepository.deleteById(id);
    }

    @Override
    public Servicio createServicio(Servicio servicio) { return  servicioRepository.save(servicio);}

    @Override
    public Servicio editServicio(EditServicioDTO editServicioDTO, Long id) {
        Servicio updateServicio = servicioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El servicio con ID " + id + " no fue encontrado."));

        updateServicio.setNombre(editServicioDTO.getNombre());
        return servicioRepository.save(updateServicio);
    }

}
