package com.example.ecomarket_servicio_productos_servicios.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomarket_servicio_productos_servicios.model.Producto;
import com.example.ecomarket_servicio_productos_servicios.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(String id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto guardarProducto(Producto producto) {
        if (producto.getName() == null || producto.getPrice() <= 0 || producto.getCategory() == null) {
            throw new IllegalArgumentException("Los campos 'nombre', 'precio', y 'categoría' son obligatorios.");
        }
        producto.setCreatedAt(new Date());
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(String id, Producto detallesProducto) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            if (detallesProducto.getName() == null || detallesProducto.getPrice() <= 0
                    || detallesProducto.getCategory() == null) {
                throw new IllegalArgumentException("Los campos 'nombre', 'precio', y 'categoría' son obligatorios.");
            }
            producto.setName(detallesProducto.getName());
            producto.setDescription(detallesProducto.getDescription());
            producto.setPrice(detallesProducto.getPrice());
            producto.setCategory(detallesProducto.getCategory());
            producto.setStock(detallesProducto.getStock());
            return productoRepository.save(producto);
        }
        return null;
    }

    public boolean eliminarProducto(String id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método para obtener productos por sellerId
    public List<Producto> obtenerProductosPorUsuario(String sellerId) {
        return productoRepository.findBySellerId(sellerId);
    }

}
