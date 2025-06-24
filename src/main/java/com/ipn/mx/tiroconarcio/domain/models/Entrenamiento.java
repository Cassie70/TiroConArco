package com.ipn.mx.tiroconarcio.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Data
@Entity
@Table(name = "Entrenamiento")
public class Entrenamiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrenamiento;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idArquero", nullable = false)
    private Arquero arquero;

    @OneToMany(mappedBy = "entrenamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Distancia> distancias = new ArrayList<>();

}
