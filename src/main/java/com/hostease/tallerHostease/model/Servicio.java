package com.hostease.tallerHostease.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "servicio")
public class Servicio {
    @Id
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "servicios")
    private Set<Hospedaje> hospedajes;
}
