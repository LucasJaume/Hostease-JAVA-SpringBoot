package com.hostease.tallerHostease.controller;


import com.hostease.tallerHostease.dto.CrearHospedajeDTO;
import com.hostease.tallerHostease.dto.EditHospedajeDTO;
import com.hostease.tallerHostease.model.Hospedaje;
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
    public ResponseEntity<Hospedaje> crearHospedaje(@RequestBody CrearHospedajeDTO crearHospedajeDTO) {
        Hospedaje nuevoHospedaje = hospedajeService.createHospedaje(crearHospedajeDTO);
        return ResponseEntity.ok(nuevoHospedaje);
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> eliminarHospedaje(@PathVariable Long id){
        hospedajeService.deleteById(id);
        return ResponseEntity.noContent().build();
        //return ResponseEntity.ok("Hospedaje eliminado exitosamente.");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Hospedaje> editHospedaje(@PathVariable Long id, @RequestBody EditHospedajeDTO editHospedajeDTO){
        Hospedaje hospedajeActualizado = hospedajeService.editHospedaje(editHospedajeDTO, id);
        return ResponseEntity.ok(hospedajeActualizado);
    }



}
