package com.ipn.mx.tiroconarcio.services.impl;

import com.ipn.mx.tiroconarcio.domain.models.Entrenamiento;
import com.ipn.mx.tiroconarcio.domain.repositories.EntrenamientoRepository;
import com.ipn.mx.tiroconarcio.services.EntrenamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenamientoServiceImpl implements EntrenamientoService {
    private final EntrenamientoRepository repository;

    @Autowired
    public EntrenamientoServiceImpl(EntrenamientoRepository entrenamientoRepository) {
        repository = entrenamientoRepository;
    }

    @Override
    public List<Entrenamiento> readAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all archers: " + e.getMessage(), e);
        }
    }

    @Override
    public Entrenamiento readById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenamiento not found with id: " + id));
    }

    @Override
    public Entrenamiento create(Entrenamiento entrenamiento) {
        if (entrenamiento == null) {
            throw new IllegalArgumentException("Entrenamiento cannot be null");
        }
        try {
            return repository.save(entrenamiento);
        } catch (Exception e) {
            throw new RuntimeException("Error creating entrenamiento: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entrenamiento not found with id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting entrenamiento with id: " + id + " - " + e.getMessage(), e);
        }
    }

    @Override
    public Entrenamiento update(Entrenamiento entrenamiento) {
        if (entrenamiento == null) {
            throw new IllegalArgumentException("Entrenamiento cannot be null");
        }
        if (!repository.existsById(entrenamiento.getIdEntrenamiento())) {
            throw new RuntimeException("Entrenamiento not found with id: " + entrenamiento.getIdEntrenamiento());
        }
        try {
            return repository.save(entrenamiento);
        } catch (Exception e) {
            throw new RuntimeException("Error updating entrenamiento: " + e.getMessage(), e);
        }

    }
}
