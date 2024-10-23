package com.example.ecomarket_servicio_productos_servicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecomarket_servicio_productos_servicios.entity.Producto;
import com.example.ecomarket_servicio_productos_servicios.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable("id") String id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("currency") String currency,
            @RequestParam("category") String category,
            @RequestParam("stock") int stock,
            @RequestParam("sellerId") String sellerId,
            @RequestParam(value = "picture", required = false) MultipartFile picture) {
        try {
            Producto producto = new Producto();
            producto.setName(name);
            producto.setDescription(description);
            producto.setPrice(price);
            producto.setCurrency(currency);
            producto.setCategory(category);
            producto.setStock(stock);
            producto.setSellerId(sellerId);

            // Ahora pasamos el producto y la imagen al servicio
            Producto nuevoProducto = productoService.guardarProducto(producto, picture);
            return ResponseEntity.status(201).body(nuevoProducto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null); // Solicitud incorrecta
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") String id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("currency") String currency,
            @RequestParam("category") String category,
            @RequestParam("stock") int stock,
            @RequestParam(value = "picture", required = false) MultipartFile picture) {
        try {
            Producto detallesProducto = new Producto();
            detallesProducto.setName(name);
            detallesProducto.setDescription(description);
            detallesProducto.setPrice(price);
            detallesProducto.setCurrency(currency);
            detallesProducto.setCategory(category);
            detallesProducto.setStock(stock);

            Producto productoActualizado = productoService.actualizarProducto(id, detallesProducto, picture);
            return productoActualizado != null ? ResponseEntity.ok(productoActualizado) : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null); // Solicitud incorrecta
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") String id) {
        boolean fueEliminado = productoService.eliminarProducto(id);
        return fueEliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{sellerId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorUsuario(@PathVariable("sellerId") String sellerId) {
        List<Producto> productos = productoService.obtenerProductosPorUsuario(sellerId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/categoria/{categoria}")
    public List<Producto> obtenerProductosPorCategoria(@PathVariable("categoria") String categoria) {
        return productoService.getProductosPorCategoria(categoria);
    }

    @GetMapping("/mas-vendidos")
    public ResponseEntity<List<Producto>> getProductosMasVendidos() {
        List<Producto> productosMasVendidos = productoService.getProductosMasVendidos();
        return new ResponseEntity<>(productosMasVendidos, HttpStatus.OK);
    }
}
