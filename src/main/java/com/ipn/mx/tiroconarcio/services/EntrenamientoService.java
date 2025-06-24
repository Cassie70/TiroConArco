package com.ipn.mx.tiroconarcio.services;

import com.ipn.mx.tiroconarcio.domain.models.Entrenamiento;

import java.util.List;

public interface EntrenamientoService {
    public List<Entrenamiento> readAll();
    public Entrenamiento readById(Long id);
    public Entrenamiento create(Entrenamiento entrenamiento);
    public void delete(Long id);
    public Entrenamiento update(Entrenamiento entrenamiento);
}
