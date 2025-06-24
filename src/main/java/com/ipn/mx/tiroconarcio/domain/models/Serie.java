package com.ipn.mx.tiroconarcio.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Serie {
    @Id
    private int idSerie;
    private int[] flechas;
}
