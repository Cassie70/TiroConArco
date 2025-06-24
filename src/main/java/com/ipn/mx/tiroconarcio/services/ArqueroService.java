package com.ipn.mx.tiroconarcio.services;

import com.ipn.mx.tiroconarcio.domain.models.Arquero;

import java.util.List;

public interface ArqueroService{
    public List<Arquero> readAll();
    public Arquero readById(Long id);
    public Arquero create(Arquero arquero);
    public void delete(Long id);
    public Arquero update(Arquero arquero);
}
