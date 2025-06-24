package com.ipn.mx.tiroconarcio.domain.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Serie")
public class Serie {
    @Id
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
    @JoinColumn(name = "idDistancia", nullable = false)
    private Distancia distancia;

}
