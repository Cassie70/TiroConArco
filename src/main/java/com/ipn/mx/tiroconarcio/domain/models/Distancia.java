package com.ipn.mx.tiroconarcio.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Distancia {
    @Id
    private int idDistancia;
    private int metros;
}
