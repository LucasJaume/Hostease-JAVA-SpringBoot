package com.hostease.tallerHostease.controller;

import com.hostease.tallerHostease.model.Pais;
import com.hostease.tallerHostease.service.IPaisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Pais")
public class paisController {

    private final IPaisService paisService;

    public paisController(IPaisService paisService) {this.paisService = paisService;}

    @GetMapping("/All")
    public ResponseEntity<List<Pais>> getAllPais(){ return ResponseEntity.ok(paisService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Pais> findById(@PathVariable Long id){

        Optional<Pais>  pais = paisService.findById(id);
        return  pais.map(ResponseEntity::ok) //RESPUESTA OK
                .orElseGet(()-> ResponseEntity.notFound().build()); //RESPUESTA ERROR
    }

    @PostMapping("/Crear")
    public ResponseEntity<Pais> crearPais(@RequestBody Pais pais) {
        try {
            Pais newPais = paisService.crearPais(pais);
            return ResponseEntity.ok(newPais);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/Delete/{id}")
    public void eliminarPais(@PathVariable Long id){
        paisService.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Pais> editPais(@PathVariable Long id, @RequestBody Pais pais){
        return ResponseEntity.ok(paisService.editarPais(pais, id));
    }




}
