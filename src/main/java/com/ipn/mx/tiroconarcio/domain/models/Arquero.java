package com.ipn.mx.tiroconarcio.domain.models;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Arquero")
public class Arquero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArquero;

    @Column(name = "nombre", nullable = false,length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false,length = 50)
    private String apellido;

    @Column(name = "marcaPersonal")
    private Integer marcaPersonal;

    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    @Column(name = "asociación", length = 100)
    private String asociación;

    @OneToMany(mappedBy = "arquero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Arco> arcos;

    @OneToMany(mappedBy = "arquero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entrenamiento> entrenamientos;

    @OneToMany(mappedBy = "arquero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Competencia> competencias;

}
