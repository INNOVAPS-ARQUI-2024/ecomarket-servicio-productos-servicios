package com.example.ecomarket_servicio_productos_servicios.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecomarket_servicio_productos_servicios.model.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
}