package com.hostease.tallerHostease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Hospedaje")
public class Hospedaje {
    @Id
    private Long ID;
    private String descripcion;
    private String imagen;
    private String precio_por_noche;
    private Instant fecha_creacion;
    private Instant fecha_modificacion;
    @ManyToOne
    @JoinColumn(name = "id_tipo_hospedaje")
    private TipoHospedaje tipoHospedaje;

    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;

    @ManyToMany
    @JoinTable(
            name = "servicio_hospedaje",
            joinColumns = @JoinColumn(name = "id_hospedaje"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private Set<Servicio> servicios;
}
