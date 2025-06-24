package com.ipn.mx.tiroconarcio.services;

import com.ipn.mx.tiroconarcio.domain.models.Serie;

import java.util.List;

public interface SerieService {
    public List<Serie> readAll();
    public Serie readById(Long id);
    public Serie create(Serie serie);
    public void delete(Long id);
    public Serie update(Serie serie);
}
