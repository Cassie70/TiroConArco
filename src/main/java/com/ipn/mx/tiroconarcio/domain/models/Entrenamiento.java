package com.ipn.mx.tiroconarcio.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Date;

@Data
@Entity
public class Entrenamiento {
    @Id
    private Long idEntrenamiento;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "idArquero", nullable = false)
    private Arquero arquero;

    @OneToMany(mappedBy = "entrenamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Distancia> distancias;

}
