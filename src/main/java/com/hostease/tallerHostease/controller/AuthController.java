package com.hostease.tallerHostease.controller;

import com.hostease.tallerHostease.dto.LoginRequestDTO;
import com.hostease.tallerHostease.service.JwtService;
import com.hostease.tallerHostease.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @PostMapping("/login")
    public  ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest autenticationRequest){
        AuthenticationResponse rsp = autenticationService.login(AuthenticationRequest);
        return  ResponseEntity.ok(rsp)
    }
}
