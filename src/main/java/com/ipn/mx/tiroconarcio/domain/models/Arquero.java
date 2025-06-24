package com.ipn.mx.tiroconarcio.domain.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Arquero {
    @Id
    private int idArquero;
    private String nombre;
    private String apellido;
    private int marcaPersonal;
    private String categoria;
    private String asociación;
}
