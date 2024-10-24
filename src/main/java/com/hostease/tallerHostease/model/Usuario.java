package com.hostease.tallerHostease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String username;
    private String password;
    private String email;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private Instant fecha_creacion;
    private Instant fecha_modificacion;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_tipo_usuario",
            joinColumns = @JoinColumn(name = "ID_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_TIPO_USUARIO"))
    private List<TipoUsuario> tipoUsuarios;

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (tipoUsuarios == null || tipoUsuarios.isEmpty()) {
            return new ArrayList<>(); // Retorna una lista vacía si no hay roles
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Recorre la lista de tipoUsuarios y agrega cada rol con el prefijo "ROLE_"
        for (TipoUsuario tipoUsuario : tipoUsuarios) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + tipoUsuario.getNombre()));
        }
        return authorities;
    }
}

