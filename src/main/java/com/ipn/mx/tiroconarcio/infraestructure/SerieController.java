package com.ipn.mx.tiroconarcio.infraestructure;

import com.ipn.mx.tiroconarcio.domain.models.Serie;
import com.ipn.mx.tiroconarcio.services.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/apiSeries")
public class SerieController {

    private final SerieService service;
    public SerieController(SerieService serieService) {
        service = serieService;
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
            return ResponseEntity.status(404).body("Serie not found with id: " + id);
        }
    }

    @GetMapping("/create")
    public ResponseEntity<?> create(@RequestBody Serie serie) {
        try {
            if (serie == null) {
                return ResponseEntity.badRequest().body("Serie cannot be null");
            }
            Serie createdSerie = service.create(serie);
            return ResponseEntity.status(201).body(createdSerie);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating serie: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Serie deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting serie with id: " + id + " - " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Serie serie) {
        try {
            if (serie == null) {
                return ResponseEntity.badRequest().body("Serie cannot be null");
            }
            Serie updatedSerie = service.update(serie);
            return ResponseEntity.ok(updatedSerie);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating serie: " + e.getMessage());
        }
    }
}