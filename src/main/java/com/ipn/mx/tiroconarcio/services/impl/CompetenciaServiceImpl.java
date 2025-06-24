package com.ipn.mx.tiroconarcio.services.impl;

import com.ipn.mx.tiroconarcio.domain.models.Competencia;
import com.ipn.mx.tiroconarcio.domain.repositories.CompetenciaRepository;
import com.ipn.mx.tiroconarcio.services.CompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompetenciaServiceImpl implements CompetenciaService {

    private final CompetenciaRepository repository;

    @Autowired
    public CompetenciaServiceImpl(CompetenciaRepository competenciaRepository) {
        repository = competenciaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Competencia> readAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all archers: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Competencia readById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competencia not found with id: " + id));
    }

    @Transactional
    @Override
    public Competencia create(Competencia competencia) {
        if (competencia == null) {
            throw new IllegalArgumentException("Competencia cannot be null");
        }
        try {
            return repository.save(competencia);
        } catch (Exception e) {
            throw new RuntimeException("Error creating competencia: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Competencia not found with id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting competencia with id: " + id + " - " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public Competencia update(Competencia competencia) {
        if (competencia == null) {
            throw new IllegalArgumentException("Competencia cannot be null");
        }
        if (!repository.existsById(competencia.getIdCompetencia())) {
            throw new RuntimeException("Competencia not found with id: " + competencia.getIdCompetencia());
        }
        try {
            return repository.save(competencia);
        } catch (Exception e) {
            throw new RuntimeException("Error updating competencia: " + e.getMessage(), e);
        }

    }
}
