package com.hostease.tallerHostease.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "ciudad")
public class Ciudad {

    @Id
    private Long id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais pais;
}
