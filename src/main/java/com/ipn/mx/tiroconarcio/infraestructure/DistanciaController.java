package com.ipn.mx.tiroconarcio.infraestructure;

import com.ipn.mx.tiroconarcio.domain.models.Distancia;
import com.ipn.mx.tiroconarcio.services.DistanciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/apiDistancias")
public class DistanciaController {

    private final DistanciaService service;
    public DistanciaController(DistanciaService distanciaService) {
        service = distanciaService;
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
            return ResponseEntity.status(404).body("Distancia not found with id: " + id);
        }
    }

    @GetMapping("/create")
    public ResponseEntity<?> create(@RequestBody Distancia distancia) {
        try {
            if (distancia == null) {
                return ResponseEntity.badRequest().body("Distancia cannot be null");
            }
            Distancia createdDistancia = service.create(distancia);
            return ResponseEntity.status(201).body(createdDistancia);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating distancia: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Distancia deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting distancia with id: " + id + " - " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Distancia distancia) {
        try {
            if (distancia == null) {
                return ResponseEntity.badRequest().body("Distancia cannot be null");
            }
            Distancia updatedDistancia = service.update(distancia);
            return ResponseEntity.ok(updatedDistancia);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating distancia: " + e.getMessage());
        }
    }
}