package com.example.ecomarket_servicio_productos_servicios.controller;

import java.util.List;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomarket_servicio_productos_servicios.entity.Servicio;
import com.example.ecomarket_servicio_productos_servicios.service.ServicioService;

@RestController
@RequestMapping("/servicios")
public class ServicioController {
    
    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> obtenerServicios() {
        return servicioService.obtenerServicios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicioPorId(@PathVariable("id") String id) {
        Servicio servicio = servicioService.obtenerServicioPorId(id);
        if (servicio != null) {
            return ResponseEntity.ok(servicio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        Servicio nuevoServicio = servicioService.guardarServicio(servicio);
        return ResponseEntity.created(URI.create("/api/servicios/" + nuevoServicio.getServiceId())).body(nuevoServicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable("id") String id,
            @RequestBody Servicio detallesServicio) {
        // Validar detallesServicio antes de actualizar
        if (detallesServicio.getName() == null || detallesServicio.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Servicio servicioActualizado = servicioService.actualizarServicio(id, detallesServicio);
        return servicioActualizado != null ? ResponseEntity.ok(servicioActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable("id") String id) {
        boolean fueEliminado = servicioService.eliminarServicio(id);
        if (fueEliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}