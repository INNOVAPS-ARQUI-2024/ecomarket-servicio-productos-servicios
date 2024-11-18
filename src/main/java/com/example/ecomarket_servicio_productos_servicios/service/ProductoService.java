package com.example.ecomarket_servicio_productos_servicios.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecomarket_servicio_productos_servicios.entity.Producto;
import com.example.ecomarket_servicio_productos_servicios.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto obtenerProductoPorId(String id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto, MultipartFile picture) {
        if (producto.getName() == null || producto.getPrice() <= 0 || producto.getCategory() == null) {
            throw new IllegalArgumentException("Los campos 'nombre', 'precio', y 'categoría' son obligatorios.");
        }

        producto.setCreatedAt(new Date());
        producto.setSold(0); // Inicializamos el campo 'sold' a 0

        if (picture != null && !picture.isEmpty()) {
            try {
                producto.setPicture(picture.getBytes()); // Guardamos la imagen como bytes
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar la imagen", e);
            }
        }

        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(String id, Producto detallesProducto, MultipartFile picture) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            if (detallesProducto.getName() == null || detallesProducto.getPrice() <= 0 || detallesProducto.getCategory() == null) {
                throw new IllegalArgumentException("Los campos 'nombre', 'precio', y 'categoría' son obligatorios.");
            }

            producto.setName(detallesProducto.getName());
            producto.setDescription(detallesProducto.getDescription());
            producto.setPrice(detallesProducto.getPrice());
            producto.setCurrency(detallesProducto.getCurrency());
            producto.setCategory(detallesProducto.getCategory());
            producto.setStock(detallesProducto.getStock());

            if (picture != null && !picture.isEmpty()) {
                try {
                    producto.setPicture(picture.getBytes()); // Actualizamos la imagen si es necesario
                } catch (IOException e) {
                    throw new RuntimeException("Error al actualizar la imagen", e);
                }
            }

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

    // Metodo para obtener productos por sellerId
    public List<Producto> obtenerProductosPorUsuario(String sellerId) {
        return productoRepository.findBySellerId(sellerId);
    }

    public List<Producto> getProductosPorCategoria(String category) {
        return productoRepository.findByCategory(category);
    }

    public List<Producto> getProductosMasVendidos() {
        // Aquí puedes usar tu lógica para obtener los productos más vendidos
        return productoRepository.findTop10ByOrderBySoldDesc();
    }
}
