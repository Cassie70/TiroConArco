package com.ipn.mx.tiroconarcio.infraestructure;

import com.ipn.mx.tiroconarcio.domain.models.Entrenamiento;
import com.ipn.mx.tiroconarcio.services.EntrenamientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/apiEntrenamientos")
public class EntrenamientoController {

    private final EntrenamientoService service;
    public EntrenamientoController(EntrenamientoService entrenamientoService) {
        service = entrenamientoService;
    }

    @GetMapping("/")
    public ResponseEntity<?> readAll() {
        try {
            return ResponseEntity.ok(service.readAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving archers: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.readById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Entrenamiento not found with id: " + id);
        }
    }

    @GetMapping("/create")
    public ResponseEntity<?> create(@RequestBody Entrenamiento entrenamiento) {
        try {
            if (entrenamiento == null) {
                return ResponseEntity.badRequest().body("Entrenamiento cannot be null");
            }
            Entrenamiento createdEntrenamiento = service.create(entrenamiento);
            return ResponseEntity.status(201).body(createdEntrenamiento);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating entrenamiento: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Entrenamiento deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting entrenamiento with id: " + id + " - " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Entrenamiento entrenamiento) {
        try {
            if (entrenamiento == null) {
                return ResponseEntity.badRequest().body("Entrenamiento cannot be null");
            }
            Entrenamiento updatedEntrenamiento = service.update(entrenamiento);
            return ResponseEntity.ok(updatedEntrenamiento);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating entrenamiento: " + e.getMessage());
        }
    }
}