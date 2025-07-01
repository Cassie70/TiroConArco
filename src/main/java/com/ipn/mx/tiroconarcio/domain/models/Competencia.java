package com.ipn.mx.tiroconarcio.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "Competencia")
public class Competencia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompetencia;

    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de término es obligatoria")
    @Column(name = "fechaTermino", nullable = false)
    private LocalDate fechaTermino;

    @Min(value = 0, message = "El puntaje clasificatorio no puede ser negativo")
    @Max(value = 720, message = "El puntaje clasificatorio no puede ser mayor a 720")
    @Column(name = "puntajeClasificatorio", nullable = false)
    private int puntajeClasificatorio;

    @Min(value = 1, message = "La posición clasificatoria debe ser al menos 1")
    @Max(value = 256, message = "La posición clasificatoria no puede ser mayor a 256")
    @Column(name = "posicionClasificatorio", nullable = false)
    private int posicionClasificatorio;

    @Min(value = 1, message = "La posición del ROI debe ser al menos 1")
    @Max(value = 128, message = "La posición del ROI no puede ser mayor a 128")
    @Column(name = "posicionROI", nullable = false)
    private int posicionROI;

    @Min(value = 0, message = "El máximo ROI no puede ser negativo")
    @Max(value = 150, message = "El máximo ROI no puede ser mayor a 150")
    @Column(name = "maximoROI", nullable = false)
    private int maximoROI;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idArquero")
    private Arquero arquero;

}
