package com.hostease.tallerHostease.controller;

import com.hostease.tallerHostease.model.PKReserva;
import com.hostease.tallerHostease.model.Reserva;
import com.hostease.tallerHostease.service.IReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Reserva")
public class reservaController {

    private final IReservaService reservaService;

    public reservaController(IReservaService reservaService) {this.reservaService = reservaService;}

    @GetMapping("/All")
    public ResponseEntity<List<Reserva>> getAllReservas() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id_hospedaje}-{id_usuario}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id_hospedaje, @PathVariable Long id_usuario) {
        PKReserva id = new PKReserva(id_hospedaje, id_usuario);
        Optional<Reserva> reserva = reservaService.findById(id);

        return reserva.map(re -> ResponseEntity.ok().body(re)) //Respuesta OK
                .orElseGet(() -> ResponseEntity.notFound().build()); //Respuesta ERROR
    }

    @PostMapping("/Crear")
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.crearReserva(reserva));
    }

    @DeleteMapping("/Delete/{id_hospedaje}-{id_usuario}")
    public void eliminarReserva(@PathVariable Long id_hospedaje, @PathVariable Long id_usuario) {
        PKReserva id = new PKReserva(id_hospedaje, id_usuario);
        reservaService.deleteByid(id);
    }

    @PutMapping("edit/{id_hospedaje}-{id_usuario}")
    public ResponseEntity<Reserva> editReserva(@PathVariable Long id_hospedaje, @PathVariable Long id_usuario, @RequestBody Reserva reserva) {
        PKReserva id = new PKReserva(id_hospedaje, id_usuario);
        Reserva updatedReserva = reservaService.editReserva(reserva, id);
        if (updatedReserva != null) {
            return ResponseEntity.ok(updatedReserva);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
