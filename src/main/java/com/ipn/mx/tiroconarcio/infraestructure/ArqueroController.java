package com.ipn.mx.tiroconarcio.infraestructure;

import com.ipn.mx.tiroconarcio.services.ArqueroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/arquero")
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
}
