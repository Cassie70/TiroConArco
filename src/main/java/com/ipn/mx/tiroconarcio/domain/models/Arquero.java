package com.ipn.mx.tiroconarcio.domain.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Arquero {
    @Id
    private int idArquero;

    @Column(name = "nombre", nullable = false,length = 50)
    private String nombre;
    private String apellido;
    private int marcaPersonal;
    private String categoria;
    private String asociación;
}
