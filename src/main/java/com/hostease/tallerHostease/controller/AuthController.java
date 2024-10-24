package com.hostease.tallerHostease.controller;

import com.hostease.tallerHostease.dto.AuthenticationRequestDTO;
import com.hostease.tallerHostease.dto.AuthenticationResponseDTO;
import com.hostease.tallerHostease.dto.SaveUserDTO;
import com.hostease.tallerHostease.model.Usuario;
import com.hostease.tallerHostease.service.AuthenticationService;
import com.hostease.tallerHostease.service.JwtService;
import com.hostease.tallerHostease.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
            @RequestBody @Valid AuthenticationRequestDTO autenticationRequest){
        AuthenticationResponseDTO rsp = authenticationService.login(autenticationRequest);
        return  ResponseEntity.ok(rsp);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody @Valid SaveUserDTO newUser) {
        // Registrar nuevo usuario
        userDetailsService.registerOneCustomer(newUser);
        // respuesta de éxito
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }


}
