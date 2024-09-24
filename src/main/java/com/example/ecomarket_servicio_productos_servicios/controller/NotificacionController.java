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

import com.example.ecomarket_servicio_productos_servicios.model.Notificacion;
import com.example.ecomarket_servicio_productos_servicios.service.NotificacionService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    // Obtener todas las notificaciones
    @GetMapping
    public List<Notificacion> obtenerNotificaciones() {
        return notificacionService.obtenerNotificaciones();
    }

    // Obtener una notificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerNotificacionPorId(@PathVariable String id) {
        Notificacion notificacion = notificacionService.obtenerNotificacionPorId(id);
        if (notificacion != null) {
            return ResponseEntity.ok(notificacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva notificación
    @PostMapping
    public Notificacion crearNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.guardarNotificacion(notificacion);
    }

    // Actualizar una notificación existente
    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizarNotificacion(@PathVariable String id, @RequestBody Notificacion detallesNotificacion) {
        Notificacion notificacionActualizada = notificacionService.actualizarNotificacion(id, detallesNotificacion);
        if (notificacionActualizada != null) {
            return ResponseEntity.ok(notificacionActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una notificación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable String id) {
        boolean fueEliminado = notificacionService.eliminarNotificacion(id);
        if (fueEliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}