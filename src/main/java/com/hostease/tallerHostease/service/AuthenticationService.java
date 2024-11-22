package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.dto.AuthenticationRequestDTO;
import com.hostease.tallerHostease.dto.AuthenticationResponseDTO;
import com.hostease.tallerHostease.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    public AuthenticationResponseDTO login(AuthenticationRequestDTO authRequest) {
        // 1. Crear un objeto de autenticación
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword()
        );

        // 2. Autenticar al usuario
        authenticationManager.authenticate(authenticationToken);

        // 3. Cargar los detalles del usuario
        Usuario usuario = (Usuario) userDetailsServiceImp.loadUserByUsername(authRequest.getUsername());

        // 4. Generar el token JWT
        String jwt = jwtService.generateToken(usuario, generateExtraClaims(usuario));

        // 5. Crear y devolver la respuesta
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        authResponse.setToken(jwt);
        authResponse.setUsername(usuario.getUsername());
        authResponse.setRole(usuario.getTipoUsuarios().get(0).getNombre()); // Asumiendo que el usuario tiene un solo rol
        authResponse.setId(usuario.getId());
        return authResponse;
    }

    //para generar reclamos adicionales
    private Map<String, Object> generateExtraClaims(Usuario user) {
        return new HashMap<>();
    }
}




