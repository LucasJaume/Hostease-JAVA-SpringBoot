package com.hostease.tallerHostease.repository;

import com.hostease.tallerHostease.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {}
