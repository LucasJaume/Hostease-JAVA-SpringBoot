package com.hostease.tallerHostease.service;


import com.hostease.tallerHostease.dto.CrearHospedajeDTO;
import com.hostease.tallerHostease.dto.EditHospedajeDTO;
import com.hostease.tallerHostease.model.Hospedaje;
import com.hostease.tallerHostease.model.Servicio;
import com.hostease.tallerHostease.repository.CiudadRepository;
import com.hostease.tallerHostease.repository.HospedajeRepository;
import com.hostease.tallerHostease.repository.ServicioRepository;
import com.hostease.tallerHostease.repository.TipoHospedajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HospedajeService implements IHospedajeService{
    @Autowired
    private HospedajeRepository hospedajeRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private TipoHospedajeRepository tipoHospedajeRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Optional<Hospedaje> findById(Long id) { return hospedajeRepository.findById(id); }

    @Override
    public List<Hospedaje> findAll() {return hospedajeRepository.findAll();}

    @Override
    public void deleteById(Long id) {
        Hospedaje hospedaje = hospedajeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El hospedaje con el ID especificado no existe."));

        hospedajeRepository.delete(hospedaje);
    }

    @Override
    public Hospedaje createHospedaje(CrearHospedajeDTO crearHospedajeDTO) {
        Hospedaje hospedaje = new Hospedaje();
        hospedaje.setDescripcion(crearHospedajeDTO.getDescripcion());
        hospedaje.setImagen(crearHospedajeDTO.getImagen());
        hospedaje.setPrecio_por_noche(crearHospedajeDTO.getPrecioPorNoche());
        Instant now = Instant.now();
        hospedaje.setFecha_creacion(now);
        hospedaje.setFecha_modificacion(now);
        // Busca la ciudad y el tipo de hospedaje por sus IDs
        hospedaje.setCiudad(ciudadRepository.findById(crearHospedajeDTO.getCiudadId()).orElse(null));
        hospedaje.setTipoHospedaje(tipoHospedajeRepository.findById(crearHospedajeDTO.getTipoHospedajeId()).orElse(null));

        Set<Servicio> servicios = crearHospedajeDTO.getServiciosIds().stream()
                .map(servicioId -> servicioRepository.findById(servicioId).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        hospedaje.setServicios(servicios);

        return hospedajeRepository.save(hospedaje);
    }

    @Override
    public Hospedaje editHospedaje(EditHospedajeDTO editHospedajeDTO, Long id) {
        Hospedaje hospedaje = hospedajeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El hospedaje con el ID especificado no existe."));

        hospedaje.setDescripcion(editHospedajeDTO.getDescripcion());
        hospedaje.setImagen(editHospedajeDTO.getImagen());
        hospedaje.setPrecio_por_noche(editHospedajeDTO.getPrecioPorNoche());

        hospedaje.setCiudad(ciudadRepository.findById(editHospedajeDTO.getCiudadId())
                .orElseThrow(() -> new NoSuchElementException("La ciudad especificada no existe.")));

        hospedaje.setTipoHospedaje(tipoHospedajeRepository.findById(editHospedajeDTO.getTipoHospedajeId())
                .orElseThrow(() -> new NoSuchElementException("El tipo de hospedaje especificado no existe.")));

        Set<Servicio> servicios = editHospedajeDTO.getServicioIds().stream()
                .map(servicioId -> servicioRepository.findById(servicioId).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        hospedaje.setServicios(servicios);

        hospedaje.setFecha_modificacion(java.time.Instant.now());
        return hospedajeRepository.save(hospedaje);
    }


}
