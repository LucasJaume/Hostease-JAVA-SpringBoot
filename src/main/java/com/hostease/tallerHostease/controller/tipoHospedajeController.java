package com.hostease.tallerHostease.controller;


import com.hostease.tallerHostease.model.TipoHospedaje;
import com.hostease.tallerHostease.service.ITipoHospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/TipoHospedaje")
public class tipoHospedajeController {

    private final ITipoHospedajeService tipoHospedajeService;

    public tipoHospedajeController(ITipoHospedajeService tipoHospedajeService) {this.tipoHospedajeService = tipoHospedajeService;}

    @GetMapping("/All")
    public ResponseEntity<List<TipoHospedaje>> getAllTipoHospedaje() {
        return ResponseEntity.ok(tipoHospedajeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoHospedaje> findById(@PathVariable Long id) {
        Optional<TipoHospedaje> tipoHospedaje = tipoHospedajeService.findById(id);

        return tipoHospedaje.map(ResponseEntity::ok) //return ok
                .orElseGet(() -> ResponseEntity.notFound().build()); //return error
    }

    @PostMapping("crear")
    public  ResponseEntity<TipoHospedaje> crearTipoHospedaje(@RequestBody TipoHospedaje tipoHospedaje) {
        return ResponseEntity.ok(tipoHospedajeService.createTipoHospedaje(tipoHospedaje));
    }

    @DeleteMapping("delete/{id}")
    public void eliminarTipoHospedaje(@PathVariable Long id) {
        tipoHospedajeService.deleteByid(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TipoHospedaje> editTipoHospedaje(@PathVariable Long id ,@RequestBody TipoHospedaje tipohospedaje) {
        TipoHospedaje tipoHospedaje = tipoHospedajeService.editTipoHospedaje(tipohospedaje, id);
        if (tipoHospedaje != null) {
            return ResponseEntity.ok(tipoHospedaje);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
