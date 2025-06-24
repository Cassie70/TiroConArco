package com.ipn.mx.tiroconarcio.services.impl;

import com.ipn.mx.tiroconarcio.domain.models.Serie;
import com.ipn.mx.tiroconarcio.domain.repositories.SerieRepository;
import com.ipn.mx.tiroconarcio.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieServiceImpl implements SerieService {
    private final SerieRepository repository;

    @Autowired
    public SerieServiceImpl(SerieRepository serieRepository) {
        repository = serieRepository;
    }

    @Override
    public List<Serie> readAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all archers: " + e.getMessage(), e);
        }
    }

    @Override
    public Serie readById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serie not found with id: " + id));
    }

    @Override
    public Serie create(Serie serie) {
        if (serie == null) {
            throw new IllegalArgumentException("Serie cannot be null");
        }
        try {
            return repository.save(serie);
        } catch (Exception e) {
            throw new RuntimeException("Error creating serie: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Serie not found with id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting serie with id: " + id + " - " + e.getMessage(), e);
        }
    }

    @Override
    public Serie update(Serie serie) {
        if (serie == null) {
            throw new IllegalArgumentException("Serie cannot be null");
        }
        if (!repository.existsById(serie.getIdSerie())) {
            throw new RuntimeException("Serie not found with id: " + serie.getIdSerie());
        }
        try {
            return repository.save(serie);
        } catch (Exception e) {
            throw new RuntimeException("Error updating serie: " + e.getMessage(), e);
        }

    }
}
