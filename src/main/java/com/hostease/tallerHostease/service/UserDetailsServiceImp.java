package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.dto.SaveUserDTO;
import com.hostease.tallerHostease.model.TipoUsuario;
import com.hostease.tallerHostease.model.TipoUsuarioEnum;
import com.hostease.tallerHostease.model.Usuario;
import com.hostease.tallerHostease.repository.TipoUsuarioRepository;
import com.hostease.tallerHostease.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;


    public Usuario registerOneCustomer(SaveUserDTO newUser) {
        validatePassword(newUser);

        Usuario usuario = new Usuario();
        usuario.setUsername(newUser.getUsername());
        usuario.setPassword(passwordEncoder.encode(newUser.getPassword()));
        usuario.setEmail(newUser.getEmail());
        usuario.setNombre(newUser.getNombre());
        usuario.setApellido(newUser.getApellido());
        usuario.setFecha_nacimiento(newUser.getFecha_nacimiento());
        usuario.setFecha_creacion(Instant.now());

        Usuario guardado = usuarioRepository.save(usuario);
        System.out.println("ID del usuario guardado: " + guardado.getId());

        List<Integer> idsTipoUsuario = new ArrayList<>();
        idsTipoUsuario.add(TipoUsuarioEnum.ANFITRION.ordinal() +1);
        idsTipoUsuario.add(TipoUsuarioEnum.INQUILINO.ordinal() +1);

        List<TipoUsuario> tipoUsuarios = tipoUsuarioRepository.findAllById(idsTipoUsuario);

        // Asigno los roles al usuario guardado
        guardado.setTipoUsuarios(tipoUsuarios);

        // Guardo el usuario con los roles asignados
        return usuarioRepository.save(guardado);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el nombre de usuario: " + username));

        // Devolver directamente el objeto `Usuario` si ya implementa `UserDetails`
        return usuario;
    }


    private void validatePassword(SaveUserDTO newUser) {
        String password = newUser.getPassword();
        if (password.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
    }

}
