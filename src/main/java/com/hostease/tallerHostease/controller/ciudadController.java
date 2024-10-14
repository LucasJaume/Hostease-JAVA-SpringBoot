package com.hostease.tallerHostease.controller;

import com.hostease.tallerHostease.model.Ciudad;
import com.hostease.tallerHostease.service.ICiudadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Ciudad")
public class ciudadController {

    private final ICiudadService ciudadService;

    public ciudadController(ICiudadService ciudadService) {this.ciudadService = ciudadService;}

    @GetMapping("/All")
    public ResponseEntity<List<Ciudad>> getAllUsuarios(){
        return ResponseEntity.ok(ciudadService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> findById(@PathVariable Long id){
        Optional<Ciudad> usuario = ciudadService.findById(id);

        return usuario.map(ResponseEntity::ok)//RESPUESTA OK
                .orElseGet(() -> ResponseEntity.notFound().build());//RESPUESTA ERROR
    }

    @PostMapping("/crear")
    public ResponseEntity<Ciudad> create(@RequestBody Ciudad ciudad){
        return ResponseEntity.ok(ciudadService.createCiudad(ciudad));
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarCiudad(@PathVariable Long id){
        ciudadService.deleteByid(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Ciudad> editCiudad(@PathVariable Long id, @RequestBody Ciudad ciudad){
        Ciudad updateCiudad = ciudadService.editCiudad(ciudad, id);
        if(updateCiudad != null){
            return ResponseEntity.ok(updateCiudad);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
