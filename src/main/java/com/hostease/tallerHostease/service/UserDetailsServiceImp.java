package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.dto.SaveUserDTO;
import com.hostease.tallerHostease.model.TipoUsuario;
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
import java.util.List;


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
        usuario.setFecha_nacimiento(newUser.getFechaNacimiento());
        usuario.setFecha_creacion(Instant.now());

        // Asigna los roles basados en los IDs recibidos
        List<TipoUsuario> tipoUsuarios = tipoUsuarioRepository.findAllById(newUser.getTipoUsuarioIds());
        usuario.setTipoUsuarios(tipoUsuarios);

        // Guarda el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el nombre de usuario: " + username));

        // Devuelve un objeto User de Spring Security con el nombre de usuario, contraseña y los roles
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());

    }

    private void validatePassword(SaveUserDTO newUser) {
        String password = newUser.getPassword();
        if (password.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
    }

}
