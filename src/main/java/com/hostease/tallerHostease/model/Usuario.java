package com.hostease.tallerHostease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuario")
public class Usuario {
    @Id
    private  long id;
    private String username;
    private String password;
    private String email;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private Instant fecha_creacion;
    private Instant fecha_modificacion;
    @ManyToMany
    @JoinTable(
            name = "usuario_tipo_usuario",
            joinColumns = @JoinColumn(name = "ID_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_TIPO_USUARIO"))
    private List<TipoUsuario> tipoUsuarios;
}
