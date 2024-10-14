package com.hostease.tallerHostease.service;


import com.hostease.tallerHostease.model.Hospedaje;
import com.hostease.tallerHostease.repository.HospedajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospedajeService implements IHospedajeService{
    @Autowired
    private HospedajeRepository hospedajeRepository;

    @Override
    public Optional<Hospedaje> findById(Long id) { return hospedajeRepository.findById(id); }

    @Override
    public List<Hospedaje> findAll() {return hospedajeRepository.findAll();}

    @Override
    public void deleteById(Long id) {  hospedajeRepository.deleteById(id); }

    @Override
    public Hospedaje createHospedaje(Hospedaje hospedaje) {return hospedajeRepository.save(hospedaje);}

    @Override
    public Hospedaje editHospedaje(Hospedaje hospedaje, Long id) {return hospedajeRepository.save(hospedaje);}
}
