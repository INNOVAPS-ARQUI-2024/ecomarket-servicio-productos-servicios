package com.example.ecomarket_servicio_productos_servicios.controller;

import java.util.List;

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

import com.example.ecomarket_servicio_productos_servicios.entity.Producto;
import com.example.ecomarket_servicio_productos_servicios.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable String id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.guardarProducto(producto);
            return ResponseEntity.status(201).body(nuevoProducto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null); // Solicitud incorrecta
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable String id,
            @RequestBody Producto detallesProducto) {
        try {
            Producto productoActualizado = productoService.actualizarProducto(id, detallesProducto);
            return productoActualizado != null ? ResponseEntity.ok(productoActualizado)
                    : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null); // Solicitud incorrecta
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        boolean fueEliminado = productoService.eliminarProducto(id);
        return fueEliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{sellerId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorUsuario(@PathVariable String sellerId) {
        List<Producto> productos = productoService.obtenerProductosPorUsuario(sellerId);
        return ResponseEntity.ok(productos);
    }
}
