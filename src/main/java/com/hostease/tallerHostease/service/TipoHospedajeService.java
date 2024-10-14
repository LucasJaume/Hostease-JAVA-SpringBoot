package com.hostease.tallerHostease.service;


import com.hostease.tallerHostease.model.TipoHospedaje;
import com.hostease.tallerHostease.repository.TipoHospedajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoHospedajeService implements ITipoHospedajeService {
    @Autowired
    private TipoHospedajeRepository tipoHospedajeRepository;

    @Override
    public Optional<TipoHospedaje> findById(Long id) {
        return  tipoHospedajeRepository.findById(id);
    }

    @Override
    public List<TipoHospedaje> findAll() { return tipoHospedajeRepository.findAll(); }

    @Override
    public void deleteByid(Long id){ tipoHospedajeRepository.deleteById(id);}

    @Override
    public TipoHospedaje createTipoHospedaje(TipoHospedaje tipoHospedaje){
        return tipoHospedajeRepository.save(tipoHospedaje);
    }

    @Override
    public TipoHospedaje editTipoHospedaje(TipoHospedaje tipoHospedaje, Long Id){
        Optional<TipoHospedaje> existingTipoHospedaje = tipoHospedajeRepository.findById(Id);
        if(existingTipoHospedaje.isPresent()){
            TipoHospedaje updatedTipoHospedaje = existingTipoHospedaje.get();
            updatedTipoHospedaje.setNombre(tipoHospedaje.getNombre());
            return tipoHospedajeRepository.save(updatedTipoHospedaje);
        }else{
            return  null;
        }
    }





}
