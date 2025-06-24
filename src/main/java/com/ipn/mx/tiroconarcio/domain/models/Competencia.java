package com.ipn.mx.tiroconarcio.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Competencia {
    @Id
    private int idCompentecia;
    private String nombre;
    private Date fechaInicio;
    private Date fechaTermino;
    private int puntajeClasificatorio;
    private int posicionClasificatorio;
    private int posicionROI;
    private int maximoROI;
}
