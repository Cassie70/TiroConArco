package com.ipn.mx.tiroconarcio.services;

import com.ipn.mx.tiroconarcio.domain.models.Distancia;

import java.util.List;

public interface DistanciaService {
    public List<Distancia> readAll();
    public Distancia readById(Long id);
    public Distancia create(Distancia distancia);
    public void delete(Long id);
    public Distancia update(Distancia distancia);
}
