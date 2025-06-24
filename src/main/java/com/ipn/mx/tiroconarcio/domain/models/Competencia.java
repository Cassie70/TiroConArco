package com.ipn.mx.tiroconarcio.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Competencia {
    @Id
    private Long idCompetencia;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fechaTermino", nullable = false)
    private LocalDate fechaTermino;

    @Column(name = "puntajeClasificatorio", nullable = false)
    private int puntajeClasificatorio;

    @Column(name = "posicionClasificatorio", nullable = false)
    private int posicionClasificatorio;

    @Column(name = "posicionROI", nullable = false)
    private int posicionROI;

    @Column(name = "maximoROI", nullable = false)
    private int maximoROI;

    @ManyToOne
    @JoinColumn(name = "idArquero", nullable = false)
    private Arquero arquero;

}
