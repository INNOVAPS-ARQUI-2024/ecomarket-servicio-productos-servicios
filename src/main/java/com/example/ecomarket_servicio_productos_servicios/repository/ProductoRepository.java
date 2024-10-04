package com.example.ecomarket_servicio_productos_servicios.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecomarket_servicio_productos_servicios.entity.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {
}
