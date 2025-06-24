package com.ipn.mx.tiroconarcio.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Distancia")
public class Distancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistancia;

    @Column(name = "metros", nullable = false)
    private int metros;

    @ManyToOne
    @JoinColumn(name = "idEntrenamiento", nullable = false)
    private Entrenamiento entrenamiento;

    @OneToMany(mappedBy = "distancia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Serie> series;

}
