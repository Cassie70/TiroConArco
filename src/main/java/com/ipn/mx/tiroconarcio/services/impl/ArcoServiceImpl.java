package com.ipn.mx.tiroconarcio.services.impl;

import com.ipn.mx.tiroconarcio.domain.models.Arco;
import com.ipn.mx.tiroconarcio.domain.repositories.ArcoRepository;
import com.ipn.mx.tiroconarcio.services.ArcoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArcoServiceImpl implements ArcoService {

    private final ArcoRepository repository;

    @Autowired
    public ArcoServiceImpl(ArcoRepository arcoRepository) {
        repository = arcoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Arco> readAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all archers: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Arco readById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arco not found with id: " + id));
    }

    @Transactional
    @Override
    public Arco create(Arco arco) {
        if (arco == null) {
            throw new IllegalArgumentException("Arco cannot be null");
        }
        try {
            return repository.save(arco);
        } catch (Exception e) {
            throw new RuntimeException("Error creating arco: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Arco not found with id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting arco with id: " + id + " - " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public Arco update(Arco arco) {
        if (arco == null) {
            throw new IllegalArgumentException("Arco cannot be null");
        }
        if (!repository.existsById(arco.getIdArco())) {
            throw new RuntimeException("Arco not found with id: " + arco.getIdArco());
        }
        try {
            return repository.save(arco);
        } catch (Exception e) {
            throw new RuntimeException("Error updating arco: " + e.getMessage(), e);
        }

    }
}
