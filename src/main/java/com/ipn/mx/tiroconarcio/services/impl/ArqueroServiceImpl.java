package com.ipn.mx.tiroconarcio.services.impl;

import com.ipn.mx.tiroconarcio.domain.models.Arquero;
import com.ipn.mx.tiroconarcio.domain.repositories.ArqueroRepository;
import com.ipn.mx.tiroconarcio.services.ArqueroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArqueroServiceImpl implements ArqueroService {

    private final ArqueroRepository repository;

    @Autowired
    public ArqueroServiceImpl(ArqueroRepository arqueroRepository) {
        repository = arqueroRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Arquero> readAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all archers: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Arquero readById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arquero not found with id: " + id));
    }

    @Transactional
    @Override
    public Arquero create(Arquero arquero) {
        if (arquero == null) {
            throw new IllegalArgumentException("Arquero cannot be null");
        }
        try {
            return repository.save(arquero);
        } catch (Exception e) {
            throw new RuntimeException("Error creating arquero: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Arquero not found with id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting arquero with id: " + id + " - " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public Arquero update(Arquero arquero) {
        if (arquero == null) {
            throw new IllegalArgumentException("Arquero cannot be null");
        }
        if (!repository.existsById(arquero.getIdArquero())) {
            throw new RuntimeException("Arquero not found with id: " + arquero.getIdArquero());
        }
        try {
            return repository.save(arquero);
        } catch (Exception e) {
            throw new RuntimeException("Error updating arquero: " + e.getMessage(), e);
        }

    }
}
