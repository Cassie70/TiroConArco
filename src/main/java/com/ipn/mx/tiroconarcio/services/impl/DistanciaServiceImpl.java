package com.ipn.mx.tiroconarcio.services.impl;

import com.ipn.mx.tiroconarcio.domain.models.Distancia;
import com.ipn.mx.tiroconarcio.domain.repositories.DistanciaRepository;
import com.ipn.mx.tiroconarcio.services.DistanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistanciaServiceImpl implements DistanciaService {
    private final DistanciaRepository repository;

    @Autowired
    public DistanciaServiceImpl(DistanciaRepository distanciaRepository) {
        repository = distanciaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Distancia> readAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all archers: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Distancia readById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Distancia not found with id: " + id));
    }

    @Transactional
    @Override
    public Distancia create(Distancia distancia) {
        if (distancia == null) {
            throw new IllegalArgumentException("Distancia cannot be null");
        }
        try {
            return repository.save(distancia);
        } catch (Exception e) {
            throw new RuntimeException("Error creating distancia: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Distancia not found with id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting distancia with id: " + id + " - " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public Distancia update(Distancia distancia) {
        if (distancia == null) {
            throw new IllegalArgumentException("Distancia cannot be null");
        }
        if (!repository.existsById(distancia.getIdDistancia())) {
            throw new RuntimeException("Distancia not found with id: " + distancia.getIdDistancia());
        }
        try {
            return repository.save(distancia);
        } catch (Exception e) {
            throw new RuntimeException("Error updating distancia: " + e.getMessage(), e);
        }

    }
}
