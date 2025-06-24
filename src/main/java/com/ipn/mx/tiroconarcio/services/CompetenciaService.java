package com.ipn.mx.tiroconarcio.services;

import com.ipn.mx.tiroconarcio.domain.models.Competencia;

import java.util.List;

public interface CompetenciaService {
    public List<Competencia> readAll();
    public Competencia readById(Long id);
    public Competencia create(Competencia competencia);
    public void delete(Long id);
    public Competencia update(Competencia competencia);
}
