package com.hostease.tallerHostease.controller;


import com.hostease.tallerHostease.model.Servicio;
import com.hostease.tallerHostease.service.IServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Servicio")
public class servicioController {
    private  final IServicioService servicioService;

    public servicioController(IServicioService servicioService) {this.servicioService = servicioService;}

    @GetMapping("/All")
    public ResponseEntity<List<Servicio>> getAllServicios() {
        return  ResponseEntity.ok(servicioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> findById(@PathVariable Long id) {
        Optional<Servicio> servicio = servicioService.findById(id);
        return servicio.map(ResponseEntity::ok)//respuesta ok
                .orElseGet(() -> ResponseEntity.notFound().build()); //respuesta error
    }

    @PostMapping("/crear")
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        return ResponseEntity.ok(servicioService.createServicio(servicio));
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarServicio(@PathVariable Long id) {
        servicioService.deleteById(id);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Servicio> editServicio(@RequestBody Servicio servicio, @PathVariable Long id) {
        Servicio updatedServicio = servicioService.editServicio(servicio, id);
        if (updatedServicio != null) {
            return ResponseEntity.ok(updatedServicio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
