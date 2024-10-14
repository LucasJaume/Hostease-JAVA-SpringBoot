package com.hostease.tallerHostease.controller;


import com.hostease.tallerHostease.model.Hospedaje;
import com.hostease.tallerHostease.service.HospedajeService;
import com.hostease.tallerHostease.service.IHospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Hospedaje")
public class hospedajeController {
    private  final IHospedajeService hospedajeService;

    public hospedajeController(IHospedajeService hospedajeService) {this.hospedajeService = hospedajeService;}

    @GetMapping("/All")
    public ResponseEntity<List<Hospedaje>> getAllHospedaje(){
        return ResponseEntity.ok(hospedajeService.findAll());
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Hospedaje> findById(@PathVariable Long Id){
        Optional<Hospedaje> hospedaje = hospedajeService.findById(Id);

        return  hospedaje.map(ResponseEntity::ok) //Retorno ok.
                .orElseGet(() -> ResponseEntity.notFound().build()); //Retorno error.
    }
    @PostMapping("/Crear")
    public ResponseEntity<Hospedaje> crearHospedaje(@RequestBody Hospedaje hospedaje){

        return ResponseEntity.ok(hospedajeService.createHospedaje(hospedaje));
    }

    @DeleteMapping("/Delete/{id}")
    public  void eliminarHospedaje(@PathVariable Long id){
        hospedajeService.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Hospedaje> editHospedaje(@PathVariable Long id, @RequestBody Hospedaje hospedaje){
        return ResponseEntity.ok(hospedajeService.editHospedaje(hospedaje,id));
    }



}
