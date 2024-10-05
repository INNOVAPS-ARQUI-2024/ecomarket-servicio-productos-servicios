package com.example.ecomarket_servicio_productos_servicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomarket_servicio_productos_servicios.model.Resena;
import com.example.ecomarket_servicio_productos_servicios.service.ResenaService;

@RestController
@RequestMapping("/api/reseñas")
@CrossOrigin(origins = "http://localhost:4200")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<Resena>> obtenerReseñas() {
        List<Resena> reseñas = resenaService.obtenerReseñas();
        if (reseñas.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(reseñas); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerReseñaPorId(@PathVariable String id) {
        Resena resena = resenaService.obtenerReseñaPorId(id);
        return resena != null ? ResponseEntity.ok(resena) : ResponseEntity.status(404).body(null); // 404 Not Found
    }

    @PostMapping
    public ResponseEntity<Resena> crearReseña(@RequestBody Resena resena) {
        if (resena == null || resena.getUserId() == null) {
            return ResponseEntity.status(400).body(null); // 400 Bad Request
        }
        Resena nuevaResena = resenaService.guardarReseña(resena);
        return ResponseEntity.status(201).body(nuevaResena); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resena> actualizarReseña(@PathVariable String id, @RequestBody Resena detallesResena) {
        Resena resenaActualizada = resenaService.actualizarReseña(id, detallesResena);
        return resenaActualizada != null ? ResponseEntity.ok(resenaActualizada) : ResponseEntity.status(404).body(null); // 404
                                                                                                                         // Not
                                                                                                                         // Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReseña(@PathVariable String id) {
        boolean fueEliminado = resenaService.eliminarReseña(id);
        return fueEliminado ? ResponseEntity.noContent().build() : ResponseEntity.status(404).build(); // 404 Not Found
    }
}
