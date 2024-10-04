package com.example.ecomarket_servicio_productos_servicios.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomarket_servicio_productos_servicios.entity.Producto;
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
        producto.setCreatedAt(new Date()); // Asigna la fecha de creación
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(String id, Producto detallesProducto) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.setName(detallesProducto.getName());
            producto.setDescription(detallesProducto.getDescription());
            producto.setPrice(detallesProducto.getPrice());
            producto.setCurrency(detallesProducto.getCurrency());
            producto.setCategory(detallesProducto.getCategory());
            producto.setStock(detallesProducto.getStock());
            producto.setSellerId(detallesProducto.getSellerId());
            producto.setReviews(detallesProducto.getReviews());
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
}
