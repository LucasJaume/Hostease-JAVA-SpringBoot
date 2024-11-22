package com.hostease.tallerHostease.controller;

import com.hostease.tallerHostease.dto.CrearReservaDTO;
import com.hostease.tallerHostease.dto.EditReservaDTO;
import com.hostease.tallerHostease.model.PKReserva;
import com.hostease.tallerHostease.model.Reserva;
import com.hostease.tallerHostease.service.IReservaService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> crearReserva(@RequestBody CrearReservaDTO crearReservaDTO) {
        try {
            Reserva nuevaReserva = reservaService.crearReserva(crearReservaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
        } catch (IllegalArgumentException e) {
            // Manejo de errores
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la reserva");
        }
    }

    @DeleteMapping("/Delete/{id_hospedaje}-{id_usuario}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long id_hospedaje, @PathVariable Long id_usuario) {
        PKReserva id = new PKReserva(id_hospedaje, id_usuario);
        try {
            reservaService.deleteByid(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("edit/{id_hospedaje}-{id_usuario}")
    public ResponseEntity<Reserva> editReserva(@PathVariable Long id_hospedaje, @PathVariable Long id_usuario, @RequestBody EditReservaDTO editReservaDTO) {
        PKReserva id = new PKReserva(id_hospedaje, id_usuario);
        Reserva reservaEditada = reservaService.editReserva(editReservaDTO,id);
        if (reservaEditada != null) {
            return ResponseEntity.ok(reservaEditada);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
