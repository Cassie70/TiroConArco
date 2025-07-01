package com.ipn.mx.tiroconarcio.infraestructure;

import com.ipn.mx.tiroconarcio.domain.models.Arquero;
import com.ipn.mx.tiroconarcio.services.ArqueroService;
import com.ipn.mx.tiroconarcio.services.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/apiArqueros")
public class ArqueroController {

    private final ArqueroService service;
    private final PdfService pdfService;
    public ArqueroController(ArqueroService arqueroService, PdfService pdfService) {
        service = arqueroService;
        this.pdfService = pdfService;
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

    @PostMapping("/")
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
            arquero.setIdArquero(id);
            Arquero updatedArquero = service.update(arquero);

            return ResponseEntity.ok(updatedArquero);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating arquero: " + e.getMessage());
        }
    }

    @GetMapping("/generarPdf/{id}")
    public ResponseEntity<byte[]> generarPdf(@PathVariable Long id) {
        try {
            Arquero arquero = service.readById(id);
            if (arquero == null) {
                return ResponseEntity.status(404).body(null);
            }

            ByteArrayInputStream pdfStream = pdfService.generarPdfArquero(arquero);

            byte[] pdfBytes = pdfStream.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=arquero_" + id + ".pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        try {
            Arquero arquero = service.readById(id);

            if (image == null || image.isEmpty()) {
                return ResponseEntity.badRequest().body("No se proporcionó una imagen.");
            }

            if (!image.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("El archivo no es un formato de imagen permitido.");
            }

            arquero.setImage(image.getBytes());
            arquero.setImageType(image.getContentType());
            service.update(arquero);

            return ResponseEntity.ok("Imagen cargada correctamente para el arquero con id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir imagen: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        try {
            Arquero arquero = service.readById(id);

            if (arquero == null || arquero.getImage() == null) {
                return ResponseEntity.status(404).body("Imagen no encontrada para el arquero con id: " + id);
            }

            MediaType mediaType = MediaType.parseMediaType(arquero.getImageType());

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(arquero.getImage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener la imagen: " + e.getMessage());
        }
    }
}