package com.ipn.mx.tiroconarcio.infraestructure;

import com.ipn.mx.tiroconarcio.domain.models.Arquero;
import com.ipn.mx.tiroconarcio.services.ArqueroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/apiArqueros")
public class ArqueroController {

    private final ArqueroService service;
    public ArqueroController(ArqueroService arqueroService) {
        service = arqueroService;
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
            return ResponseEntity.status(404).body("Arquero not found with id: " + id);
        }
    }

    @GetMapping("/create")
    public ResponseEntity<?> create(@RequestBody Arquero arquero) {
        try {
            if (arquero == null) {
                return ResponseEntity.badRequest().body("Arquero cannot be null");
            }
            Arquero createdArquero = service.create(arquero);
            return ResponseEntity.status(201).body(createdArquero);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating arquero: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Arquero deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting arquero with id: " + id + " - " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Arquero arquero) {
        try {
            if (arquero == null) {
                return ResponseEntity.badRequest().body("Arquero cannot be null");
            }
            Arquero updatedArquero = service.update(arquero);
            return ResponseEntity.ok(updatedArquero);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating arquero: " + e.getMessage());
        }
    }
}
