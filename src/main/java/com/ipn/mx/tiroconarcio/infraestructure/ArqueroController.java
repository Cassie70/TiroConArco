package com.ipn.mx.tiroconarcio.infraestructure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ipn.mx.tiroconarcio.domain.models.Arquero;
import com.ipn.mx.tiroconarcio.domain.models.ArqueroDTO;
import com.ipn.mx.tiroconarcio.services.ArqueroService;
import com.ipn.mx.tiroconarcio.services.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    public ResponseEntity<?> create(@RequestBody ArqueroDTO request) {
        try {
            if (request == null) {
                return ResponseEntity.badRequest().body("Arquero cannot be null");
            }
            Arquero arquero = new Arquero();
            arquero.setNombre(request.getNombre());
            arquero.setApellido(request.getApellido());
            arquero.setMarcaPersonal(request.getMarcaPersonal());
            arquero.setCategoria(request.getCategoria());
            arquero.setAsociación(request.getAsociacion());
            arquero.setImage(null);
            arquero.setImageType(null);

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
            Map<String, String> response = Map.of("message", "Arquero deleted successfully", "id", id.toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting arquero with id: " + id + " - " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ArqueroDTO request) {
        try {
            if (request == null) {
                return ResponseEntity.badRequest().body("Arquero cannot be null");
            }

            Arquero arquero = service.readById(id);
            if (arquero == null) {
                return ResponseEntity.status(404).body("Arquero with id " + id + " not found");
            }

            arquero.setNombre(request.getNombre());
            arquero.setApellido(request.getApellido());
            arquero.setMarcaPersonal(request.getMarcaPersonal());
            arquero.setCategoria(request.getCategoria());
            arquero.setAsociación(request.getAsociacion());

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
            return ResponseEntity.status(500).body(
                    ("Error generating PDF for arquero with id: " + id + " - " + e.getMessage()).getBytes()
            );
        }
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        try {
            Arquero arquero = service.readById(id);
            Map<String,String> response = new HashMap<>();

            if (image == null || image.isEmpty()) {
                response.put("message", "Arquero image cannot be empty");
                return ResponseEntity.badRequest().body(response);
            }

            if (!Objects.requireNonNull(image.getContentType()).startsWith("image/")) {
                response.put("message", "Arquero image is not an image");
                return ResponseEntity.badRequest().body(response);
            }

            arquero.setImage(image.getBytes());
            arquero.setImageType(image.getContentType());
            service.update(arquero);

            response.put("message", "Image uploaded successfully for arquero with id: " + id);
            response.put("id", id.toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error uploading image for arquero with id: " + id + " - " + e.getMessage());
            return ResponseEntity.status(500).body(response);
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