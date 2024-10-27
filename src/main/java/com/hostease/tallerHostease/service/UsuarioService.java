package com.hostease.tallerHostease.service;

import com.hostease.tallerHostease.dto.EditUserDTO;
import com.hostease.tallerHostease.dto.SaveUserDTO;
import com.hostease.tallerHostease.model.Usuario;
import com.hostease.tallerHostease.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository UsuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Usuario> findById(Long id) {
        return UsuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return UsuarioRepository.findAll();
    }

    @Override
    public void deleteByid(Long id) { UsuarioRepository.deleteById(id); }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return UsuarioRepository.save(usuario);
    }

    @Override
    public Usuario editUsuario(EditUserDTO updatedUser, Long id) {
        Optional<Usuario> existingUsuario = UsuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
            Usuario updatedUsuario = existingUsuario.get();

            // Validar si el nuevo correo ya está en uso
            if (!updatedUser.getEmail().equals(updatedUsuario.getEmail()) &&
                    UsuarioRepository.existsByEmail(updatedUser.getEmail())) {
                throw new IllegalArgumentException("El correo electrónico ya está en uso por otro usuario.");
            }

            updatedUsuario.setUsername(updatedUser.getUsername());
            updatedUsuario.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Asegúrate de cifrar la contraseña si es necesario
            updatedUsuario.setEmail(updatedUser.getEmail());
            updatedUsuario.setNombre(updatedUser.getNombre());
            updatedUsuario.setApellido(updatedUser.getApellido());
            updatedUsuario.setFecha_modificacion(Instant.now());

            return UsuarioRepository.save(updatedUsuario);
        } else {
            return null;
        }
    }


    @Override
    @Transactional
    public Optional<Usuario> findByUsername(String username) {
        return UsuarioRepository.findByUsername(username);
    }
}
