package com.ipn.mx.tiroconarcio.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Serie")
public class Serie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSerie;

    @Column(name = "flecha1", nullable = false)
    private int flecha1;

    @Column(name = "flecha2", nullable = false)
    private int flecha2;

    @Column(name = "flecha3", nullable = false)
    private int flecha3;

    @Column(name = "flecha4", nullable = false)
    private int flecha4;

    @Column(name = "flecha5", nullable = false)
    private int flecha5;

    @Column(name = "flecha6", nullable = false)
    private int flecha6;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idDistancia", nullable = false)
    private Distancia distancia;

}
