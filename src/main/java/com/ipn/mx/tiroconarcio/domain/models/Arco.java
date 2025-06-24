package com.ipn.mx.tiroconarcio.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Arco {
    @Id
    private int idArco;
    private String tipo;
    private String marca;
    private int libraje;
    private float apertura;
    private float peso;
}
