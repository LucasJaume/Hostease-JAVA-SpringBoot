package com.hostease.tallerHostease.controller;

import com.hostease.tallerHostease.model.TipoUsuario;
import com.hostease.tallerHostease.service.ITipoUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/TipoUsuario")
public class TipoUsuarioController {

    private final ITipoUsuarioService tipoUsuarioService;

    public TipoUsuarioController(ITipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @GetMapping("/All")
    public ResponseEntity<List<TipoUsuario>> GetAllTipoUsuario() {
        return ResponseEntity.ok(tipoUsuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> findById(@PathVariable Integer id) {
        Optional<TipoUsuario> tipoUsuario = tipoUsuarioService.findById(id);
        return tipoUsuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<TipoUsuario> createTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        return ResponseEntity.ok(tipoUsuarioService.crearTipoUsuario(tipoUsuario));
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarTipoUsuario(@PathVariable Integer id) {
        tipoUsuarioService.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TipoUsuario> editTipoUsuario(@PathVariable Integer id, @RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario updateTipoUsuario= tipoUsuarioService.editarTipoUsuario(tipoUsuario, id);
        if (updateTipoUsuario != null) {
            return ResponseEntity.ok(updateTipoUsuario);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
