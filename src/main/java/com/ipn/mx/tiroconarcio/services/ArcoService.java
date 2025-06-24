package com.ipn.mx.tiroconarcio.services;

import com.ipn.mx.tiroconarcio.domain.models.Arco;

import java.util.List;

public interface ArcoService {
    public List<Arco> readAll();
    public Arco readById(Long id);
    public Arco create(Arco arco);
    public void delete(Long id);
    public Arco update(Arco arco);
}
