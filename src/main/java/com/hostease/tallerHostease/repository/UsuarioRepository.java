package com.hostease.tallerHostease.repository;


import com.hostease.tallerHostease.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String email);
    Optional<Usuario> findByEmail(String email); //
    boolean existsByEmail(String email); // metodo para verificar si el email existe
}
