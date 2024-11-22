package com.hostease.tallerHostease.controller;

import com.hostease.tallerHostease.dto.AuthenticationRequestDTO;
import com.hostease.tallerHostease.dto.AuthenticationResponseDTO;
import com.hostease.tallerHostease.dto.SaveUserDTO;
import com.hostease.tallerHostease.service.AuthenticationService;
import com.hostease.tallerHostease.service.JwtService;
import com.hostease.tallerHostease.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserDetailsServiceImp userDetailsService;


    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody AuthenticationRequestDTO authenticationRequest) {
        AuthenticationResponseDTO response = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> registerUser(
            @RequestBody @Valid SaveUserDTO newUser) {
        // Registrar nuevo usuario
        userDetailsService.registerOneCustomer(newUser);
        // respuesta de éxito
        return ResponseEntity.ok().build();
    }


}
