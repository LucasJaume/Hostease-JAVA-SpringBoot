package com.hostease.tallerHostease.controller;


import com.hostease.tallerHostease.model.Usuario;
import com.hostease.tallerHostease.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Usuario")
public class usuarioController {

    private final IUsuarioService usuarioService;

    public  usuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/All")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){

        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.findById(id);

        return usuario.map(ResponseEntity::ok) //retorno 200 ok
                .orElseGet(() -> ResponseEntity.notFound().build()); //retorno error
    }

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.createUsuario(usuario));
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        usuarioService.deleteByid(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Usuario> editUsuario(@PathVariable Long id, @RequestBody Usuario usuario){

        Usuario updatedUsuario = usuarioService.editUsuario(usuario, id);
        if (updatedUsuario != null) {
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}