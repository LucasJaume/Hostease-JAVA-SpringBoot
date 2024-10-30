package com.hostease.tallerHostease.controller;


import com.hostease.tallerHostease.dto.EditServicioDTO;
import com.hostease.tallerHostease.model.Servicio;
import com.hostease.tallerHostease.service.IServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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
    public ResponseEntity<String> eliminarServicio(@PathVariable Long id) {
        servicioService.deleteById(id);
        return ResponseEntity.ok("Servicio eliminado con éxito");
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editServicio(@RequestBody EditServicioDTO editServicioDTO, @PathVariable Long id) {
        try {
            Servicio updatedServicio = servicioService.editServicio(editServicioDTO, id);
            return ResponseEntity.ok("Servicio actualizado con éxito: " + updatedServicio);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El servicio con ID " + id + " no fue encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el servicio.");
        }
    }

}
