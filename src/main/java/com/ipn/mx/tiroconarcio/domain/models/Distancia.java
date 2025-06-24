package com.ipn.mx.tiroconarcio.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Distancia")
public class Distancia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistancia;

    @Column(name = "metros", nullable = false)
    private int metros;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idEntrenamiento", nullable = false)
    private Entrenamiento entrenamiento;

    @OneToMany(mappedBy = "distancia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Serie> series = new ArrayList<>();

}
