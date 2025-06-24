package com.ipn.mx.tiroconarcio.infraestructure;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"})
@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "¡Tiro con arco está vivo!";
    }
}
