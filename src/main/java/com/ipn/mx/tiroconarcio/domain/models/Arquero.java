package com.ipn.mx.tiroconarcio.domain.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Arquero")
public class Arquero implements Serializable {
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
    @JsonManagedReference
    private List<Arco> arcos = new ArrayList<>();

    @OneToMany(mappedBy = "arquero", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Entrenamiento> entrenamientos = new ArrayList<>();

    @OneToMany(mappedBy = "arquero", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Competencia> competencias = new ArrayList<>();

    @Lob
    @JsonIgnore
    @Column(name = "image")
    private byte[] image;

    @JsonIgnore
    @Column(name = "image_type")
    private String imageType;

}
