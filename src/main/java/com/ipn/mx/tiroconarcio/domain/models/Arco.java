package com.ipn.mx.tiroconarcio.domain.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Arco {
    @Id
    private Long idArco;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "libraje", nullable = false)
    private int libraje;

    @Column(name = "apertura", nullable = false)
    private float apertura;

    @Column(name = "peso", nullable = false)
    private float peso;

    @ManyToOne
    @JoinColumn(name = "idArquero", nullable = false)
    private Arquero arquero;

}
