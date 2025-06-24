package com.ipn.mx.tiroconarcio.infraestructure;

import com.ipn.mx.tiroconarcio.domain.models.Arco;
import com.ipn.mx.tiroconarcio.services.ArcoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/apiArcos")
public class ArcoController {

    private final ArcoService service;
    public ArcoController(ArcoService arcoService) {
        service = arcoService;
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
            return ResponseEntity.status(404).body("Arco not found with id: " + id);
        }
    }

    @GetMapping("/create")
    public ResponseEntity<?> create(@RequestBody Arco arco) {
        try {
            if (arco == null) {
                return ResponseEntity.badRequest().body("Arco cannot be null");
            }
            Arco createdArco = service.create(arco);
            return ResponseEntity.status(201).body(createdArco);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating arco: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Arco deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting arco with id: " + id + " - " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Arco arco) {
        try {
            if (arco == null) {
                return ResponseEntity.badRequest().body("Arco cannot be null");
            }
            Arco updatedArco = service.update(arco);
            return ResponseEntity.ok(updatedArco);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating arco: " + e.getMessage());
        }
    }
}