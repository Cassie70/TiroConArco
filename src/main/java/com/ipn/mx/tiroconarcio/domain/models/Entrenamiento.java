package com.ipn.mx.tiroconarcio.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Entrenamiento {
    @Id
    private int idEntrenamiento;
    private Date fecha;
}
