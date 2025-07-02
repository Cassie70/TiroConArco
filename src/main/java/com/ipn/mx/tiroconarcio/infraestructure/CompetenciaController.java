package com.ipn.mx.tiroconarcio.infraestructure;

import com.ipn.mx.tiroconarcio.domain.models.Competencia;
import com.ipn.mx.tiroconarcio.services.CompetenciaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/apiCompetencias")
public class CompetenciaController {

    private final CompetenciaService service;

    public CompetenciaController(CompetenciaService competenciaService) {
        this.service = competenciaService;
    }

    @GetMapping("/")
    public ResponseEntity<?> readAll() {
        try {
            return ResponseEntity.ok(service.readAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving competencias: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.readById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Competencia not found with id: " + id);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody Competencia competencia) {
        try {
            Competencia createdCompetencia = service.create(competencia);
            return ResponseEntity.status(201).body(createdCompetencia);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating competencia: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Competencia competencia) {
        try {
            competencia.setIdCompetencia(id);
            Competencia updatedCompetencia = service.update(competencia);
            return ResponseEntity.ok(updatedCompetencia);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating competencia: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            Map<String, String> response = Map.of("message", "Competencia deleted successfully", "id", id.toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting competencia with id: " + id + " - " + e.getMessage());
        }
    }
}
